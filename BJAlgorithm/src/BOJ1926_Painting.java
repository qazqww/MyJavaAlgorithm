// https://www.acmicpc.net/problem/1926

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1926_Painting {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		int cnt = 0;
		int maxArea = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (map[r][c] == '1') {
					cnt++;
					int area = 0; 
					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(r, c));
					map[r][c] = '2';
					
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						area++;
						for (int d = 0; d < 4; d++) {
							int ny = p.y + dy[d];
							int nx = p.x + dx[d];
							
							if (ny >= n || ny < 0 || nx >= m || nx < 0 || map[ny][nx] != '1')
								continue;
							
							map[ny][nx] = '2';
							queue.offer(new Point(ny, nx));
						}
					}
					maxArea = Math.max(maxArea, area);
				}
			}
		}
		
		System.out.println(cnt + "\n" + maxArea);
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