package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2169_RobotControl {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0 왼쪽 1 아래 2 오른쪽
        int[] dy = new int[] { 0, 1, 0 };
        int[] dx = new int[] { -1, 0, 1 };
        int[][][] dp = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1_000_000_000);
            }
        }
        Arrays.fill(dp[0][0], map[0][0]);
        Queue<int[]> queue = new LinkedList<>(); // y, x, 온 방향
        queue.offer(new int[] { 0, 0, 1 });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 3; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                if (ny >= N || ny < 0 || nx >= M || nx < 0) {
                    continue;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(Arrays.toString(dp[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}
