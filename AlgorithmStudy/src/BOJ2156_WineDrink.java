// https://www.acmicpc.net/problem/2156

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156_WineDrink {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		int[] wine = new int[n+1];
		for (int i = 1; i <= n; i++)
			wine[i] = Integer.parseInt(in.readLine());
		
		int[] dp = new int[n+1];
		dp[1] = wine[1];
		if (n >= 2)
			dp[2] = wine[1] + wine[2];
		
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i-1];
			int temp = Math.max(dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i]);
			dp[i] = Math.max(dp[i], temp);
		}
		
		System.out.println(dp[n]);
	}
}