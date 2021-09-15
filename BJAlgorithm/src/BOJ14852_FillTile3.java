// https://www.acmicpc.net/problem/14852

import java.util.Scanner;

public class BOJ14852_FillTile3 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long[] dp = new long[N + 1];
		long[] sum = new long[N + 1];
		dp[0] = 1;
		dp[1] = 2;
		if (N >= 2) dp[2] = 7;

		sum[0] = dp[0];
		sum[1] = sum[0] + dp[1];
		if (N >= 2) sum[2] = sum[1] + dp[2];
		
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i-2] * 3 % 1_000_000_007 + dp[i-1] * 2 % 1_000_000_007) % 1_000_000_007;
			
			dp[i] = (dp[i] + (sum[i-3] * 2) % 1_000_000_007) % 1_000_000_007;
			
			sum[i] = (sum[i-1] + dp[i]) % 1_000_000_007;
		}
		
		System.out.println(dp[N]);
	}
}