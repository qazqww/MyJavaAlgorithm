// https://www.acmicpc.net/problem/2293

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293_Coin1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];
        int[][] dp = new int[n][k+1];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(in.readLine());
        }

        for (int i = 0; i < k+1; i++) {
            if (i % coin[0] == 0) {
                dp[0][i] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k + 1; j++) {
                if (j < coin[i]) {
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] + dp[i][j - coin[i]];
                }
            }
        }

        System.out.println(dp[n-1][k]);
    }
}

/*
  k 0 1 2 3 4 5 6 7 8 9 10
n
1   1 1 1 1 1 1 1 1 1 1 1
2   1 1 2 2 3 3 4 4 5 5 6
5   1 1 2 2 3 4 5 6 7 8 10

(k-5, n) + (k, n-1)


10
5 5
5 2 2 1
5 2 1 1 1
5 1 1 1 1 1
=> 5 이하의 수로 5(k - n)를 만든 횟수
2 2 2 2 2
2 2 2 2 1 1
2 2 2 1 1 1 1
2 2 1 1 1 1 1 1
2 1 1 1 1 1 1 1 1
=> 2 이하의 수로 8을 만든 횟수
1 1 1 1 1 1 1 1 1 1
=> 1 이하의 수로 9를 만든 횟수
 */