package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16948_DeathKnight {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        String[] temp = in.readLine().split(" ");
        int r1 = Integer.parseInt(temp[0]);
        int c1 = Integer.parseInt(temp[1]);
        int r2 = Integer.parseInt(temp[2]);
        int c2 = Integer.parseInt(temp[3]);

        int[] dy = new int[] { -2, -2, 0, 0, 2, 2 };
        int[] dx = new int[] { -1, 1, -2, 2, -1, 1 };
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], 1_000_000);
        }
        map[r1][c1] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { r1, c1 });
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == r2 && cur[1] == c2) {
                System.out.println(map[cur[0]][cur[1]]);
                return;
            }
            for (int d = 0; d < 6; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] <= map[cur[0]][cur[1]] + 1) {
                    continue;
                }

                map[ny][nx] = map[cur[0]][cur[1]] + 1;
                queue.offer(new int[] { ny, nx });
            }
        }
        System.out.println(-1);
    }
}