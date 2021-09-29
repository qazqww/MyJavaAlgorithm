// https://www.acmicpc.net/problem/4485

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4485_IsGreenClothesZelda {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Point> pq;
		
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		int t = 1;
		while (true) {
			int N = Integer.parseInt(in.readLine());
			if (N == 0)
				break;
			
			int[][] map = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = -1;
			visited[0][0] = true;
			pq = new PriorityQueue<>();
			pq.offer(new Point(0, 0, map[0][0]));
			
			while (!pq.isEmpty()) {
				Point p = pq.poll();
				
				if (p.y == N-1 && p.x == N-1) {
					answer = p.v;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx])
						continue;
					
					visited[ny][nx] = true;
					pq.offer(new Point(ny, nx, p.v + map[ny][nx]));
				}
			}
			
			System.out.printf("Problem %d: %d\n", t++, answer);
		}
	}
	
	static class Point implements Comparable<Point> {
		int y, x, v;
		
		public Point(int y, int x, int v) {
			this.y = y;
			this.x = x;
			this.v = v;
		}
		
		@Override
		public int compareTo(Point o) {
			return Integer.compare(v, o.v);
		}
	}
}