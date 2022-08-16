// https://www.acmicpc.net/problem/2206

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2206_BreakWallAndMove {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		int R = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		boolean[][] map = new boolean[R][C];
		int[][][] dp = new int[R][C][2];
		for (int r = 0; r < R; r++) {
			String line = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c) == '0' ? false : true;
				dp[r][c][0] = Integer.MAX_VALUE;
				dp[r][c][1] = Integer.MAX_VALUE;
			}
		}
		
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 0));
		dp[0][0][0] = 1;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				
				if (ny >= R || ny < 0 || nx >= C || nx < 0)
					continue;

				int height = p.h;
				if (map[ny][nx])
					height++;
				
				if (height > 1)
					continue;
				
				if (dp[ny][nx][height] > dp[p.y][p.x][p.h] + 1) {
					dp[ny][nx][height] = dp[p.y][p.x][p.h] + 1;
					queue.offer(new Point(ny, nx, height));
				}
			}
		}
		
		int answer = Math.min(dp[R-1][C-1][0], dp[R-1][C-1][1]);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		
	}
	
	static class Point {
		int y;
		int x;
		int h;
		public Point(int y, int x, int floor) {
			this.y = y;
			this.x = x;
			this.h = floor;
		}
	}
}
