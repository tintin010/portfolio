//숫자고르기

#include <iostream>
#include <cstring>
using namespace std;

int n = 0, res = 0;
int arr[101] = { 0 };
int visited[101] = { 0 };
int result[101] = { 0 };

void dfs(int st, int c) {
	if (!visited[c]) {	//방문 X
		visited[c] = 1;
		dfs(st, arr[c]);
	}
	else {	//방문 O
		if (st == c) {	// 처음의 값과 같은가? (사이클을 찾아야 함)
			//cout << "순환" << c << endl;
			for (int i = 1; i <= n; i++) {
				if (visited[i] == 1) {
					if (result[i] == 0) {
						result[i] = i;
					}
				}
			}
		}
	}
}


int main() {
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> arr[i];
	}
	for (int i = 1; i <= n; i++) {
		dfs(i, i);
		memset(visited, 0, sizeof(visited));
	}
	for (int i = 1; i <= n; i++) {
		if (result[i] != 0) res++;
	}
	cout << res << endl;
	
	for (int i = 1; i <= n; i++) {
		if (result[i] != 0) cout << result[i] << endl;
	}
}