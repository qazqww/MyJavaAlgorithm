// https://www.acmicpc.net/problem/17070

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17070_MovingPipe1 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		boolean[][] isBlocked = new boolean[N][N];
		boolean[][] visited = new boolean[N][N];
		int[][][] map = new int[N][N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				if (st.nextToken().equals("1"))
					isBlocked[i][j] = true;
			}
		}
		
		PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> (a.x + a.y) - (b.x + b.y));
		pq.offer(new Point(0, 1));
		map[0][1][0] = 1;
		
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			
			// 다음 가로
			if (p.x + 1 < N && !isBlocked[p.y][p.x + 1]) {
				map[p.y][p.x + 1][0] += map[p.y][p.x][0];
				map[p.y][p.x + 1][0] += map[p.y][p.x][1];
				
				if (!visited[p.y][p.x + 1]) {
					pq.offer(new Point(p.y, p.x + 1));
					visited[p.y][p.x + 1] = true;
				}
			}
			
			// 다음 대각선
			if (p.x + 1 < N && p.y + 1 < N && !isBlocked[p.y + 1][p.x + 1]
					 && !isBlocked[p.y][p.x + 1] && !isBlocked[p.y + 1][p.x]) {
				map[p.y + 1][p.x + 1][1] += map[p.y][p.x][0];
				map[p.y + 1][p.x + 1][1] += map[p.y][p.x][1];
				map[p.y + 1][p.x + 1][1] += map[p.y][p.x][2];
				
				if (!visited[p.y + 1][p.x + 1]) {
					pq.offer(new Point(p.y + 1, p.x + 1));
					visited[p.y + 1][p.x + 1] = true;
				}
			}
			
			// 다음 세로
			if (p.y + 1 < N && !isBlocked[p.y + 1][p.x]) {
				map[p.y + 1][p.x][2] += map[p.y][p.x][1];
				map[p.y + 1][p.x][2] += map[p.y][p.x][2];
				
				if (!visited[p.y + 1][p.x]) {
					pq.offer(new Point(p.y + 1, p.x));
					visited[p.y + 1][p.x] = true;
				}
			}
		}
		
		System.out.println(map[N - 1][N - 1][0] + map[N - 1][N - 1][1] + map[N - 1][N - 1][2]);
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