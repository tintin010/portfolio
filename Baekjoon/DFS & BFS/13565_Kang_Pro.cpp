#include <iostream>
#include <queue>
using namespace std;

int map[1002][1002] = { 0 };
int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };

int main() {
    int M, N; cin >> M >> N;

    for (int i = 1; i <= M; i++) {
        for (int j = 1; j <= N; j++) {
            char tmp; cin >> tmp;
            map[i][j] = !(tmp - '0');
        }
    }

    queue<pair<int, int>> q;
    for (int i = 1; i <= N; i++) {
        if (map[1][i]) {
            q.push(make_pair(1, i));
            map[1][i] = 0;
        }
    }

    while (!q.empty()) {
        int x = q.front().first, y = q.front().second;
        if (x == M) { cout << "YES"; return 0; }
        for (int i = 0; i < 4; i++) {
            if (map[x + dx[i]][y + dy[i]]) {
                q.push(make_pair(x + dx[i], y + dy[i]));
                map[x + dx[i]][y + dy[i]] = 0;
            }
        }
        q.pop();
    }

    cout << "NO";

    return 0;
}