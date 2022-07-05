// https://www.acmicpc.net/problem/2468

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2468_SafeArea {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		int maxHeight = -1;
		int answer = 1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}
		
		boolean[][] visited;
		for (int i = 1; i < maxHeight; i++) {
			visited = new boolean[N][N];
			int safe = 0;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					Queue<Point> queue = new LinkedList<>();
					
					if (map[r][c] > i && !visited[r][c]) {
						safe++;
						queue.offer(new Point(r, c));
						
						while (!queue.isEmpty()) {
							Point p = queue.poll();
							for (int d = 0; d < 4; d++) {
								int ny = p.y + dy[d];
								int nx = p.x + dx[d];
								
								if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] <= i || visited[ny][nx])
									continue;
								
								visited[ny][nx] = true;
								queue.offer(new Point(ny, nx));
							}
						}
					}
				}
			}
			answer = Math.max(answer, safe);
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