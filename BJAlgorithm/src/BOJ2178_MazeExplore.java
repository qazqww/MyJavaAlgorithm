// https://www.acmicpc.net/problem/2178

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2178_MazeExplore {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[][] dp = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			map[i] = st.nextToken().toCharArray();
			Arrays.fill(dp[i], 1_000_000);
		}
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		int answer = -1;
		
		PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
		pq.offer(new Point(0, 0, 0));
		dp[0][0] = 0;
		
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			
			if (p.y == N-1 && p.x == M-1) {
				answer = p.dist;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				
				if (ny >= N || ny < 0 || nx >= M || nx < 0 || map[ny][nx] == '0')
					continue;
				
				if (dp[ny][nx] > dp[p.y][p.x] + 1) {
					dp[ny][nx] = dp[p.y][p.x] + 1;
					pq.offer(new Point(ny, nx, p.dist + 1));
				}
			}
		}
		
		System.out.println(answer + 1);
	}
	
	static class Point {
		int y;
		int x;
		int dist;
		public Point(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
}