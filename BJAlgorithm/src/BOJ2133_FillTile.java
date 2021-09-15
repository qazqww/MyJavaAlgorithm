// https://www.acmicpc.net/problem/2133

import java.util.Scanner;

public class BOJ2133_FillTile {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] dp = new int[N+1];
		int[] sum = new int[N+1];
		if (N >= 2) {
			dp[2] = 3;
			sum[2] = 3;
		}
		for (int i = 4; i <= N; i += 2) {
			dp[i] = dp[i-2] * 3 + 2;
			dp[i] += sum[i-4] * 2;
			sum[i] = sum[i-2] + dp[i];
		}
		
		System.out.println(dp[N]);
	}
}