// https://www.acmicpc.net/problem/2579

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2579_StairClimb {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] stairs = new int[N];
		for (int i = 0; i < N; i++) {
			stairs[i] = Integer.parseInt(in.readLine());
		}
		
		int[] dp = new int[N];
		
		dp[0] = stairs[0];
		if (N == 1) {
			System.out.println(dp[0]);
			return;
		}
		
		dp[1] = stairs[0] + stairs[1];
		if (N == 2) {
			System.out.println(dp[1]);
			return;
		}
		
		dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];
		if (N == 3) {
			System.out.println(dp[2]);
		}
		
		for (int i = 3; i < N; i++) {
			dp[i] = Math.max(dp[i-3] + stairs[i-1] + stairs[i], dp[i-2] + stairs[i]);
		}
		
		System.out.println(dp[N-1]);
	}
}
