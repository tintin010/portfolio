#include <iostream>
#include <queue>
#include <string>
#include <memory.h>

using namespace std;

int n, cnt = 0;
int visit[102][102] = { 0, };
queue <pair<int, int>> q;
char map[102][102];


int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };

void dfs(int x,int y) {
	if (visit[x][y]) return;
	visit[x][y] = 1;
	for (int i = 0; i < 4; i++) {
		int xx = dx[i] + x;
		int yy = dy[i] + y;
		if (xx <= 0 || yy <= 0 || xx > n || yy > n) continue;
		if (visit[xx][yy] == 0 && map[xx][yy] == map[x][y]) dfs(xx, yy);
	}
}

int main(void) {
	string result = "";
	cin >> n;
	string arr = "";
	for (int i = 1; i <= n; i++) {
		cin >> arr;
		for (int j = 1; j <= n; j++) map[i][j] = arr[j-1];
	}
	cnt = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (visit[i][j]==0) {
				dfs(i, j);
				cnt++;
			}
		}
	}
	result += to_string(cnt) + " ";

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if(map[i][j]=='G') map[i][j] = 'R';	//적록색맹
		}
	}
	memset(visit, 0, sizeof(visit));
	cnt = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (visit[i][j] == 0) {
				dfs(i, j);
				cnt++;
			}
		}
	}
	result += to_string(cnt);
	cout << result << endl;
}
