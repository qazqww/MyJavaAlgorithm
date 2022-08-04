// https://www.acmicpc.net/problem/1245

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1245_FarmManage {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int[] dy = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dx = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		boolean[][] potential = new boolean[R][C];
		boolean[][] visited = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				potential[r][c] = true;
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				for (int d = 0; d < dy.length; d++) {
					int ny = r + dy[d];
					int nx = c + dx[d];
					
					if (ny >= R || ny < 0 || nx >= C || nx < 0)
						continue;
					
					if (map[r][c] < map[ny][nx]) {
						potential[r][c] = false;
						break;
					}
				}
			}
		}
		
		Queue<Point> queue;
		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				
				if (!potential[r][c] || map[r][c] == 0)
					continue;
				
				queue = new LinkedList<>();
				queue.offer(new Point(r, c));
				visited[r][c] = true;
				potential[r][c] = false;
				boolean success = true;
				
				while (!queue.isEmpty()) {
					Point p = queue.poll();
					for (int d = 0; d < dy.length; d++) {
						int ny = p.y + dy[d];
						int nx = p.x + dx[d];
						
						if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx])
							continue;
						
						if (map[ny][nx] == map[r][c]) {
							if (!potential[ny][nx])
								success = false;
							
							queue.offer(new Point(ny, nx));
							visited[ny][nx] = true;
							potential[ny][nx] = false;
						}
					}
				}
				
				if (success)
					answer++;
					
			}
		}
		
		System.out.println(answer);
	}
	
	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
