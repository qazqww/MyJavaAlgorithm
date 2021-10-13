// https://www.acmicpc.net/problem/15486

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15486_Resign2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		
		int[] time = new int[N+1];
		int[] pay = new int[N+1];
		int[] dp = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			int endDay = i + time[i] - 1; // 상담이 끝나는 날 계산
			if (endDay <= N) // 퇴사일 전에 끝낼 수 있다면
				dp[endDay] = Math.max(dp[i-1] + pay[i], dp[endDay]); // 끝나는 날에 전날 번 돈 + 현재 상담으로 벌 돈 비교
			
			dp[i] = Math.max(dp[i], dp[i-1]); // 현재 날짜가 전날보다 적으면 전날 결과를 가져옴
		}
			
		System.out.println(dp[N]);
	}
}