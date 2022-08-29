// https://www.acmicpc.net/problem/2573

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573_Iceberg {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		int[][] map = new int[N][M];
		
		List<Point> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] > 0)
					list.add(new Point(i, j));
			}
		}
		
		int time = 0;
		while (true) {
			time++;
			
			// 녹일 곳 찾기
			for (Point p : list) {
				int sea = 0;
				for (int d = 0; d < 4; d++) {
					int ny = p.y + dy[d];
					int nx = p.x + dx[d];
					
					if (ny >= N || ny < 0 || nx >= M || nx < 0)
						continue;
					
					if (map[ny][nx] == 0)
						sea++;
				}
				p.melt = sea;
			}
			
			// 녹이기
			for (int i = list.size() - 1; i >= 0; i--) {
				Point p = list.get(i);
				map[p.y][p.x] -= p.melt;
				
				if (map[p.y][p.x] <= 0) {
					map[p.y][p.x] = 0;
					list.remove(i);
				}
			}
			
			// 분리 체크
			boolean[][] visited = new boolean[N][M];
			Queue<Point> queue = new LinkedList<>();
			
			if (list.isEmpty()) {
				System.out.println(0);
				break;
			}
			
			Point first = list.get(0);
			queue.offer(first);
			visited[first.y][first.x] = true;
			int length = 0;
			
			while (!queue.isEmpty()) {
				length++;
				Point p = queue.poll();
				for (int d = 0; d < 4; d++) {
					int ny = p.y + dy[d];
					int nx = p.x + dx[d];
					
					if (ny >= N || ny < 0 || nx >= M || nx < 0 || visited[ny][nx])
						continue;
					
					if (map[ny][nx] > 0) {
						queue.offer(new Point(ny, nx));
						visited[ny][nx] = true;
					}
				}
			}
			
			if (length != list.size()) {
				System.out.println(time);
				break;
			}
		}
	}
	
	static class Point {
		int y;
		int x;
		int melt;
		
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}