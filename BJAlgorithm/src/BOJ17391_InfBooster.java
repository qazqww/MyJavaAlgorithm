// https://www.acmicpc.net/problem/17391

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17391_InfBooster {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 1_000_000);
		}
		dp[0][0] = 0;
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { 0, 0 });
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int y = now[0];
			int x = now[1];
			
			int mov = map[y][x];
			
			for (int i = 1; i <= Math.min(mov, N - 1 - y); i++) {
				if (dp[y][x] + 1 < dp[y + i][x]) {
					dp[y + i][x] = dp[y][x] + 1;
					queue.offer(new int[] { y + i, x });
				}
			}
			
			for (int i = 1; i <= Math.min(mov, M - 1 - x); i++) {
				if (dp[y][x] + 1 < dp[y][x + i]) {
					dp[y][x + i] = dp[y][x] + 1;
					queue.offer(new int[] { y, x + i });
				}
			}
		}
		System.out.println(dp[N - 1][M - 1]);
	}
}