// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA1868_PoppingMine {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };	// 위부터 시계방향으로 8방 탐색
		int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(in.readLine());
			char[][] map = new char[N][N];
			
			for (int r = 0; r < N; r++)
				map[r] = in.readLine().toCharArray();
			
			// step 1. 근처에 지뢰가 있는 칸은 n으로 둔다
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != '*')
						continue;
					
					for (int i = 0; i < 8; i++) {
						int nr = r + dy[i];
						int nc = c + dx[i];
						
						if (nr >= N || nr < 0 || nc >= N || nc < 0 || map[nr][nc] != '.')
							continue;
						
						map[nr][nc] = 'n';
					}
				}
			}
			
			// step 2. 0을 찾아 bfs로 탐색하여, 드러나는 곳은 v로 둔다. (시작점으로 삼는 0의 개수만큼 답 증가)
			int answer = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != '.')
						continue;
					
					Queue<Point> queue = new LinkedList<>();
					map[r][c] = 'v';
					queue.offer(new Point(r, c));
					answer++;
					
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						for (int i = 0; i < 8; i++) {
							int nr = p.y + dy[i];
							int nc = p.x + dx[i];
							
							if (nr >= N || nr < 0 || nc >= N || nc < 0 || map[nr][nc] == 'v')
								continue;
							
							if (map[nr][nc] == '.')
								queue.offer(new Point(nr, nc));
							
							map[nr][nc] = 'v';
						}
					}
				}
			}
			
			// step 3. v가 아닌 곳(n인 곳)의 개수만큼 답에 더한다.
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 'n')
						answer++;
				}
			}
			
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
	
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}