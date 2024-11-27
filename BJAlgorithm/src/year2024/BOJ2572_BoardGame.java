package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2572_BoardGame {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] colors = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            char rgb = st.nextToken().charAt(0);
            colors[i] = (rgb == 'R') ? 1 : (rgb == 'G') ? 2 : 3;
        }

        st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            char rgb = st.nextToken().charAt(0);
            int color = (rgb == 'R') ? 1 : (rgb == 'G') ? 2 : 3;
            map[start][end] = color;
            map[end][start] = color;
        }

        int[][] dp = new int[N + 1][M]; // 횟수, 마을;
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0, 0 }); // 위치, 이동 횟수, 점수
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] >= N || cur[2] < dp[cur[1]][cur[0]]) {
                continue;
            }
            for (int i = 0; i < M; i++) {
                int newScore = cur[2];
                int destColor = map[cur[0]][i];
                if (destColor == 0) {
                    continue;
                }

                if (colors[cur[1]] == destColor) {
                    newScore += 10;
                }
                if (dp[cur[1] + 1][i] < newScore) {
                    dp[cur[1] + 1][i] = newScore;
                    queue.offer(new int[] { i, cur[1] + 1, newScore });
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N + 1; i++) {
            for (int val : dp[i]) {
                answer = Math.max(answer, val);
            }
        }
        System.out.println(answer);
    }
}
/*

 */