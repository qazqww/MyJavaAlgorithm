// https://www.acmicpc.net/problem/3197

// 시간 초과

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3197_SwanLake {
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		boolean[][] visited;
		
		List<Point> swans = new ArrayList<>();
		Queue<Point> queue = new LinkedList<>();
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		for (int r = 0; r < R; r++) {
			String row = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = row.charAt(c);
				if (map[r][c] == 'L') {
					swans.add(new Point(r, c));
				}
			}
		}
		
		int day = 0;
		loop: while (true) {
			day++;
			visited = new boolean[R][C];
			
			// 녹을 빙판 체크
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == '.') {
						map[r][c] = 'M';
						visited[r][c] = true;
						queue.offer(new Point(r, c));
						
						while (!queue.isEmpty()) {
							Point p = queue.poll();
							for (int i = 0; i < 4; i++) {
								int ny = p.y + dy[i];
								int nx = p.x + dx[i];
								
								if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx])
									continue;
								
								if (map[ny][nx] == '.') {
									visited[ny][nx] = true;
									queue.offer(new Point(ny, nx));
								}
								else if (map[ny][nx] == 'X')
									map[ny][nx] = 'M';
							}
						}
					}
				}
			}
			
			// 빙판 녹이기
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 'M')
						map[r][c] = '.';
				}
			}
			
			// 백조의 만남 여부 체크
			visited = new boolean[R][C];
			Point swan = swans.get(0);
			visited[swan.y][swan.x] = true;
			queue.offer(new Point(swan.y, swan.x));
			
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				for (int i = 0; i < 4; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx] || map[ny][nx] == 'X')
						continue;
					
					if (map[ny][nx] == '.') {
						visited[ny][nx] = true;
						queue.offer(new Point(ny, nx));
					}
					else if (map[ny][nx] == 'L') {
						break loop;
					}
				}
			}
		}
		
		System.out.println(day);

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
