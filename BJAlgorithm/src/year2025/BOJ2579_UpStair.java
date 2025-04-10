package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579_UpStair {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(in.readLine());
        }

        int[][] dp = new int[N + 1][2];
        dp[1][0] = score[0];
        for (int i = 2; i < N + 1; i++) {
            dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + score[i - 1];
            dp[i][1] = dp[i - 1][0] + score[i - 1];
        }

        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }
}