// https://www.acmicpc.net/problem/2589

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2589_TreasureIsland {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");

		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		int R = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		char[][] map = new char[R][C];
		
		boolean[][] visited;
		Queue<Point> queue = new LinkedList<>();
		
		for (int r = 0; r < R; r++) {
			String temp2 = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = temp2.charAt(c);
			}
		}
		
		int max = 0;
		
		// 맵을 순회하면서 각 점 별로 가장 먼 지점까지의 거리 탐색
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'W')
					continue;
				
				// 탐색을 시작할 때마다 방문여부, 큐 초기화
				visited = new boolean[R][C];
				queue.clear();
				
				// 시작점 집어넣기
				queue.offer(new Point(r, c));
				visited[r][c] = true;
				
				// 레벨을 관리하는 BFS
				int level = -1;
				while(!queue.isEmpty()) {
					level++;
					int size = queue.size();
					for (int i = 0; i < size; i++) {
						Point p = queue.poll();
						
						for (int d = 0; d < 4; d++) {
							int ny = p.y + dy[d];
							int nx = p.x + dx[d];
							
							if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == 'W' || visited[ny][nx])
								continue;
							
							queue.offer(new Point(ny, nx));
							visited[ny][nx] = true;
						}
					}
				}
				
				// 한 점의 탐색을 마치면 거리의 최대값 갱신
				max = Math.max(max, level);
			}
		}
		
		System.out.println(max);
	}
	
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}



/* 플로이드-와샬 : 시간초과
public class BOJ2589_TreasureIsland {
	
	static int R, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");

		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		char[][] map = new char[R][C];
		int[][] graph = new int[R*C][R*C];
		
		for (int r = 0; r < R; r++) {
			String temp2 = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = temp2.charAt(c);
			}
		}
		
		for (int r = 0; r < R*C; r++) {
			Arrays.fill(graph[r], 10_000);
			graph[r][r] = 0;
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				
				if (map[r][c] == 'W')
					continue;
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dy[i];
					int nc = c + dx[i];
					
					if (nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc] == 'W')
						continue;
					
					graph[getIndex(r, c)][getIndex(nr, nc)] = 1;
					graph[getIndex(nr, nc)][getIndex(r, c)] = 1;
				}
			}
		}
		
		for (int k = 0; k < R*C; k++) {
			for (int i = 0; i < R*C; i++) {
				for (int j = 0; j < R*C; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		int max = 0;
		for (int r = 0; r < R*C; r++) {
			for (int c = 0; c < R*C; c++) {
				if (graph[r][c] >= 10000)
					continue;
				max = Math.max(max, graph[r][c]);
			}
		}
		
		System.out.println(max);
	}
	
	static int getIndex(int r, int c) { return r * C + c; }
	
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
*/