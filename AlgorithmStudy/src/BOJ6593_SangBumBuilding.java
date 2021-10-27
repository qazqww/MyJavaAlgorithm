// https://www.acmicpc.net/problem/6593

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593_SangBumBuilding {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dz = { 0, 0, 0, 0, 1, -1 };
		int[] dy = { 0, 0, 1, -1, 0, 0 };
		int[] dx = { 1, -1, 0, 0, 0, 0 }; 
		
		while (true) {
			st = new StringTokenizer(in.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			// 종료 조건
			if (L + R + C == 0) break;
			
			char[][][] map = new char[L][R][C];
			boolean[][][] visited = new boolean[L][R][C];
			Point start = null;
			
			String temp;
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++) {
					temp = in.readLine(); 
					for (int c = 0; c < C; c++) {
						map[l][r][c] = temp.charAt(c);
						
						// 시작지점 저장
						if (map[l][r][c] == 'S') {
							start = new Point(l, r, c);
							visited[l][r][c] = true;
						}
					}
				}
				in.readLine();
			}
			
			Queue<Point> queue = new LinkedList<>();
			queue.offer(start);
			
			int time = 0;
			boolean escaped = false;
			
			// depth를 관리하는 BFS
			loop: while (!queue.isEmpty()) {
				
				int size = queue.size();
				time++;
				
				for (int i = 0; i < size; i++) {
					Point p = queue.poll();
					
					for (int d = 0; d < 6; d++) {
						int nz = p.z + dz[d];
						int ny = p.y + dy[d];
						int nx = p.x + dx[d];
						
						if (nz < 0 || nz >= L || ny < 0 || ny >= R || nx < 0 || nx >= C // 맵을 벗어날 경우
								|| visited[nz][ny][nx] || map[nz][ny][nx] == '#') // 방문했던 곳이거나 막혔을 경우
							continue;
						
						if (map[nz][ny][nx] == 'E') { // 도달한 지점이 출구라면 종료
							escaped = true;
							break loop;
						}
						
						queue.offer(new Point(nz, ny, nx));
						visited[nz][ny][nx] = true;
					}
				}
			}
			
			System.out.println(escaped ? "Escaped in " + time + " minute(s)." : "Trapped!");
		}

	}
	
	static class Point {
		int z, y, x;

		public Point(int z, int y, int x) {
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}
}