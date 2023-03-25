// 구슬 찾기

#include <iostream>
#include <cstring>
#include <queue>
using namespace std;

int N, M, stdard=0,down,up,result;
int map[101][101] = {0};
int visited[101] = { 0 };

void d_dfs(int st) {	// 가벼운 것 확인
	visited[st] = 1;
	for (int i = 1; i <= N; i++) {
		if (visited[i] == 0 && map[st][i] == 1) {
			down++;
			if (down >= stdard) break;
			d_dfs(i);
		}
	}
}

void u_dfs(int st) {	// 무거운 것 확인
	visited[st] = 1;
	for (int i = 1; i <= N; i++) {
		if (visited[i] == 0 && map[st][i] == -1) {
			up++;
			if (up >= stdard) break;
			u_dfs(i);

		}
	}
}

void dfs(int st) {	// 처음 확인

	for (int i = 1; i <= N; i++) {
		
		visited[st] = 1;
		if (up >= stdard || down >= stdard) {
			result++;
			break;
		}
		if (visited[i] == 0 && map[st][i] == -1) {	//기준 구슬이 가볍다
			up++;
			if (up >= stdard) {
				result++;
				break;
			}
			u_dfs(i);
		}
		else if(visited[i] == 0 && map[st][i] == 1)
		{
			if (down >= stdard) {
				result++;
				break;
			}
			down++;
			d_dfs(i);
		} 
	}
}

int main() {

	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		map[a][b] = 1;
		map[b][a] = -1;
	}
	stdard = N / 2 + 1;
	for (int i = 1; i <= N; i++) {
		memset(visited, 0, sizeof(visited));
		up = 0;
		down = 0;
		dfs(i);
	}
	cout << result << endl;
}