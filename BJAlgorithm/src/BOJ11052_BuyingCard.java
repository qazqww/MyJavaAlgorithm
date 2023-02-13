// https://www.acmicpc.net/problem/11052

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11052_BuyingCard {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = i; j < N + 1; j++) {
				dp[j] = Math.max(dp[j], dp[j - i] + arr[i - 1]);
			}
		}
		
		System.out.println(dp[N]);
	}
}