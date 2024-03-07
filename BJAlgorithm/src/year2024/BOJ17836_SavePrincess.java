package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17836_SavePrincess {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int shortcut = -1;
        boolean canUseShortcut = false;
        boolean justGoal = false;
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    shortcut = N - i - 1 + M - j - 1;
                }
            }
        }

        int[] dy = new int[] { 0, 1, 0, -1 };
        int[] dx = new int[] { 1, 0, -1, 0 };
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });
        visited[0][0] = true;
        int step = -1;
        loop: while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            if (step > T) {
                break;
            }
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();

                if (cur[0] == N - 1 && cur[1] == M - 1) {
                    justGoal = true;
                    break loop;
                }
                else if (map[cur[0]][cur[1]] == 2) {
                    shortcut += step;
                    canUseShortcut = true;
                }

                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];

                    if (ny >= N || ny < 0 || nx >= M || nx < 0 || map[ny][nx] == 1 || visited[ny][nx]) {
                        continue;
                    }

                    visited[ny][nx] = true;
                    queue.offer(new int[] { ny, nx });
                }
            }
        }

        int answer = -1;
        if (justGoal) {
            answer = step;
            if (canUseShortcut) {
                answer = Math.min(answer, shortcut);
            }
        }
        else {
            if (canUseShortcut && shortcut <= T) {
                answer = shortcut;
            }
            else {
                System.out.println("Fail");
                return;
            }
        }
        System.out.println(answer);
    }
}
/*
6 6 16
0 1 0 0 0 0
0 0 0 1 1 0
0 1 1 0 0 0
2 0 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0

 */