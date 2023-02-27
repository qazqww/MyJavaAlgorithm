// https://www.acmicpc.net/problem/11060

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11060_JumpJump {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] maze = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			maze[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { 0, 0 });
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			
			if (now[0] == N - 1) {
				System.out.println(now[1]);
				return;
			}
			
			for (int i = 1; i <= maze[now[0]]; i++) {
				int next = now[0] + i;
				
				if (next >= N)
					break;
				
				if (dp[next] > now[1] + 1) {
					dp[next] = now[1] + 1;
					queue.offer(new int[] { next, now[1] + 1 });
				}
			}
		}
		
		System.out.println(-1);
	}
}