// https://www.acmicpc.net/problem/6087

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ6087_LaserCommu {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		int C = Integer.parseInt(temp[0]);
		int R = Integer.parseInt(temp[1]);

		ArrayList<Point> points = new ArrayList<>();
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		int[][] map = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			String temp2 = in.readLine();
			for (int c = 0; c < C; c++) {
				if (temp2.charAt(c) == '*')
					map[r][c] = -9;
				else
					map[r][c] = -5;
				
				if (temp2.charAt(c) == 'C') {
					if (points.isEmpty()) {
						points.add(new Point(r, c, -1));
						map[r][c] = 0;
					}
					else {
						points.add(new Point(r, c, -1));
						map[r][c] = Integer.MAX_VALUE;
					}
				}
			}
		}
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(points.get(0));
		
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = p.y;
				int nx = p.x;
				
				while (true) {
					ny += dy[d];
					nx += dx[d];
					
					if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == -9)
						break;
					
					if (map[ny][nx] < 0 || map[ny][nx] > p.cnt + 1) {
						map[ny][nx] = p.cnt + 1;
						pq.offer(new Point(ny, nx, p.cnt + 1));
					}
				}
			}
		}
		
		System.out.println(map[points.get(1).y][points.get(1).x]);
	}
	
	static class Point implements Comparable<Point> {
		int y;
		int x;
		int cnt;
		
		public Point(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Point o) {
			return cnt > o.cnt ? -1 : 1;
		}
	}
}