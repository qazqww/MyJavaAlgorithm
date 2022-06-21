// https://www.acmicpc.net/problem/2579

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2579_StairClimb {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] stairs = new int[N];
		for (int i = 0; i < N; i++) {
			stairs[i] = Integer.parseInt(in.readLine());
		}
		
		if (N == 1) {
			System.out.println(stairs[0]);
			return;
		}
		else if (N == 2) {
			System.out.println(stairs[0] + stairs[1]);
			return;
		}
		else if (N == 3) {
			System.out.println(Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]));
			return;
		}
		
		int[] dp = new int[N];
		dp[0] = stairs[0];
		dp[1] = stairs[0] + stairs[1];
		dp[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);
		
		for (int i = 3; i < N-1; i++) {
			int result1 = dp[i-3] + stairs[i-1] + stairs[i];
			int result2 = dp[i-2] + stairs[i];
			int result3 = dp[i-1];
			int max = Math.max(result1, result2);
			max = Math.max(max, result3);
			dp[i] = max;
		}
		
		System.out.println(Math.max(dp[N-1 - 3] + stairs[N-2] + stairs[N-1], dp[N-1 - 2] + stairs[N-1]));
	}
}
