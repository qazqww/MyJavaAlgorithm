// https://www.acmicpc.net/problem/1890

// unsolved

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1890_Jump {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		long[][] dp = new long[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 1;
		for (int i = 0; i < N*2 - 2; i++) {
			for (int j = 0; j <= i; j++) {
				if (j >= N || i-j >= N || dp[j][i-j] == 0)
					continue;
				
				int jump = map[j][i-j];
				if (j + jump < N)
					dp[j+jump][i-j] += dp[j][i-j];
				if (i-j + jump < N)
					dp[j][i-j+jump] += dp[j][i-j];
			}
		}
		
		System.out.println(dp[N-1][N-1]);
	}
	
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}