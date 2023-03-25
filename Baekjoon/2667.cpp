//단지 번호 붙이기
#include <iostream>
#include <queue>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int map[27][27];
int visit[27][27];
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
queue <pair<int, int>> q;
vector <int> v;
int side;



void BFS() {
	int count = 1;
	while (!q.empty()) {
		int coloum = q.front().first;
		int row = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int c = coloum + dy[i];
			int r = row + dx[i];
			if (c > 0 && r > 0 && c <= side && r <= side && visit[c][r] == 0 && map[c][r] == 1) {
				visit[c][r] = 1;
				q.push(make_pair(c, r));
				count++;
			}
		}
	}
	v.push_back(count);
}

int main(void) {
	cin >> side;
	for (int i = 1; i <= side; i++) {
		string str = "";
		cin >> str;
		for (int j = 1; j <= side; j++) map[i][j] = str[j - 1] - '0';
	}

	for (int i = 1; i <= side; i++) {
		for (int j = 1; j <= side; j++) {
			if (visit[i][j] == 0 && map[i][j] == 1) {
				q.push(make_pair(i, j));
				visit[i][j] = 1;
				BFS();
			}
		}
	}
	sort(v.begin(), v.end());
	cout << v.size() << endl;
	for (int i = 0; i < v.size(); i++)cout << v[i] << endl;
}