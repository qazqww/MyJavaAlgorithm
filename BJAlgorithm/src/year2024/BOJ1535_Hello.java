package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1535_Hello {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int[][] info = new int[N][2]; // { 체력, 기쁨 }

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            info[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[2][100];
        for (int i = 0; i < N; i++) {
            // info[i][0] : 체력, info[i][1] : 기쁨
            for (int j = info[i][0]; j < 100; j++) {
                dp[1][j] = Math.max(dp[0][j - info[i][0]] + info[i][1], dp[0][j]);
            }
            System.arraycopy(dp[1], 0, dp[0], 0, 100);
        }
        System.out.println(dp[0][99]);
    }
}