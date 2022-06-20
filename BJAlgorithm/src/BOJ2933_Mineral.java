// https://www.acmicpc.net/problem/2933

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2933_Mineral {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		boolean[][] visited = new boolean[R][C];
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		for (int r = 0; r < R; r++) {
			map[r] = in.readLine().toCharArray();
		}
		
		int N = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			int y = R - Integer.parseInt(st.nextToken());
			int x = -1;
			
			// 막대가 부딪히는 지점 구하기
			if (i % 2 == 0) { // 왼쪽에서 공격
				for (x = 0; x < C; x++) {
					if (map[y][x] == 'x')
						break;
				}
				if (x == C) // 막대가 그대로 통과
					continue;
			}
			else { // 오른쪽에서 공격
				for (x = C-1; x >= 0; x--) {
					if (map[y][x] == 'x')
						break;
				}
				if (x == -1) // 막대가 그대로 통과
					continue;
			}
			
			map[y][x] = '.';
			
			// 바닥과 이어진 클러스터 탐색
			Queue<Point> queue;
			visited = new boolean[R][C];
			for (int c = 0; c < C; c++) {
				
				queue = new LinkedList<>();
				if (map[R-1][c] == 'x' && !visited[R-1][c]) {
					visited[R-1][c] = true;
					queue.offer(new Point(R-1, c));
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						for (int d = 0; d < 4; d++) {
							int ny = p.y + dy[d];
							int nx = p.x + dx[d];
							
							if (ny >= R || ny < 0 || nx >= C || nx < 0
									|| visited[ny][nx] || map[ny][nx] == '.')
								continue;
							
							visited[ny][nx] = true;
							queue.offer(new Point(ny, nx));
						}
					}
				}
			}
			
			// 바닥과 떨어진 클러스터 탐색
			List<Point> fallList = new ArrayList<>();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 'x' && !visited[r][c]) {
						fallList.add(new Point(r, c));
					}
				}
			}

			int size = fallList.size();
			 
			if (size == 0)
				continue;
			
			while (true) {
				// 클러스터 추락 가능성 체크
				boolean isFall = true;
				for (int k = 0; k < size; k++) {
					Point p = fallList.get(k);
					
					if (p.y + 1 >= R || (map[p.y + 1][p.x] == 'x' && visited[p.y + 1][p.x])) {
						isFall = false;
						break;
					}
				}
				
				// 클러스터 추락
				if (isFall) {
					for (int k = 0; k < size; k++) {
						Point p = fallList.get(size - k - 1);
						map[p.y][p.x] = '.';
						map[p.y + 1][p.x] = 'x';
						fallList.set(size - k - 1, new Point(p.y + 1, p.x));
					}
				}
				else
					break;
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
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
