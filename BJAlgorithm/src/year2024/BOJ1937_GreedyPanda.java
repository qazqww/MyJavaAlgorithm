package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937_GreedyPanda {
    static int[] dy = new int[] { -1, 0, 1, 0 };
    static int[] dx = new int[] { 0, 1, 0, -1 };
    static int N;
    static int[][] map;
    static int[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        visited = new int[N][N][2];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int up = dfsUp(i, j);
                int down = dfsDown(i, j);
                answer = Math.max(answer, up + down - 1);
            }
        }
        System.out.println(answer);
    }

    static int dfsUp(int y, int x) {
        int cnt = 1;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny >= N || ny < 0 || nx >= N || nx < 0) {
                continue;
            }
            if (map[ny][nx] > map[y][x]) {
                if (visited[ny][nx][0] > 0) {
                    cnt = Math.max(cnt, 1 + visited[ny][nx][0]);
                }
                else {
                    cnt = Math.max(cnt, 1 + dfsUp(ny, nx));
                }
            }
        }
        visited[y][x][0] = cnt;
        return cnt;
    }
    static int dfsDown(int y, int x) {
        int cnt = 1;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny >= N || ny < 0 || nx >= N || nx < 0) {
                continue;
            }
            if (map[ny][nx] < map[y][x]) {
                if (visited[ny][nx][1] > 0) {
                    cnt = Math.max(cnt, 1 + visited[ny][nx][1]);
                }
                else {
                    cnt = Math.max(cnt, 1 + dfsDown(ny, nx));
                }
            }
        }
        visited[y][x][1] = cnt;
        return cnt;
    }
}
/*
1. 큰 방향으로 탐색하기
2. 작은 방향으로 탐색하기
3. 큰 방향 개수와 작은 방향 개수의 편차 기록하기

- 탐색하며 방문한 점을 체크 => int[2] { 이 점으로부터 ~~ 방향으로 갔을 때 그 끝점과 이 곳의 거리 }
 */