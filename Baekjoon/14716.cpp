//현수막
#include <iostream>
#include <queue>
#include <string>

using namespace std;

int map[252][252];
queue <pair<int, int>> q;
int cnt = 0;
int col, row;
int dx[8] = { 0,1,1,1,0,-1,-1,-1 };//북쪽부터 시계방향으로 돈다.
int dy[8] = { -1,-1,0,1,1,1,0,-1 };


void bfs() {

	while (!q.empty()) 
	{
		int c = q.front().first;
		int r = q.front().second;
		q.pop();
		for (int i = 0; i < 8; i++) {
			int pos_c = c + dy[i];
			int pos_r = r + dx[i];
			if (pos_c > 0 && pos_r > 0 && pos_c <= col && pos_r <= row && map[pos_c][pos_r] == 1) {
				map[pos_c][pos_r] = 0;
				q.push(make_pair(pos_c, pos_r));
			}
		}
	}
	cnt++;
}

int main(void) {

	cin >> col >> row;
	for (int i = 1; i <= col; i++)
		for (int j = 1; j <= row; j++)
			cin >> map[i][j];
	//cout << "입력 끝" << endl;
	for (int i = 1; i <= col; i++) {
		for (int j = 0; j <= row; j++) {
			if (map[i][j] == 1) {
				q.push(make_pair(i, j));
				map[i][j] = 0;
				//cout << "i : " << i << "j : " << j << endl;
				bfs();
			}
		}
	}
	cout << cnt << endl;
}