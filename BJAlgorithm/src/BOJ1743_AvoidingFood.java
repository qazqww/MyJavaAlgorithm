// https://www.acmicpc.net/problem/1743

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1743_AvoidingFood {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] <= 0) {
                    continue;
                }

                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] { i, j });
                map[i][j] = -1;
                int cnt = 0;
                while (!queue.isEmpty()) {
                    int[] p = queue.poll();
                    cnt++;
                    for (int d = 0; d < 4; d++) {
                        int ny = p[0] + dy[d];
                        int nx = p[1] + dx[d];

                        if (ny >= N || ny < 0 || nx >= M || nx < 0 || map[ny][nx] <= 0) {
                            continue;
                        }

                        queue.offer(new int[] { ny, nx });
                        map[ny][nx] = -1;
                    }
                }

                max = Math.max(max, cnt);
            }
        }

        System.out.println(max);
    }
}