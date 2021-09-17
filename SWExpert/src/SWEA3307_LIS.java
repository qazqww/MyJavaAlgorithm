// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBOKg-a6l0DFAWr

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3307_LIS {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(in.readLine());
			int[] arr = new int[N];
			int[] dp = new int[N];
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				dp[i] = 1;
			}
			
			int max = 0;
			for (int i = 1; i < N; i++) {
				for (int j = i; j >= 0; j--) {
					if (arr[j] < arr[i] && dp[i] <= dp[j]) {
						dp[i] = dp[j] + 1;
						if (max < dp[i])
							max = dp[i];
					}
				}
			}
			
			System.out.printf("#%d %d\n", t+1, max);
		}
	}
}