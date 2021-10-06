// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SWEA1249_SupplyRoute {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(in.readLine());
			
			int[][] map = new int[N][N];	// 파여진 도로 정보를 담음
			int[][] route = new int[N][N];	// 해당 위치까지 걸리는 최소 시간을 담음
			boolean[][] visited = new boolean[N][N];	// 방문 여부를 담음
			
			// 입력 부분
			for (int r = 0; r < N; r++) {
				Arrays.fill(route[r], 10_000_000);
				String temp = in.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = temp.charAt(c) - '0';
				}
			}
			route[0][0] = 0;
			
			// 복구 시간이 적은 위치부터 탐색할 수 있록 우선순위 큐 사용
			PriorityQueue<Point> pq = new PriorityQueue<>();
			pq.offer(new Point(0, 0, route[0][0]));
			visited[0][0] = true;
			
			int answer = 0;
			while(!pq.isEmpty()) {	// 시간이 가장 적게 걸리는 위치부터 계속적으로 사방탐색 진행
				Point p = pq.poll();
				
				if (p.y == N-1 && p.x == N-1) {		// 도착점에 도달했을 경우
					answer = p.v;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx])
						continue;

					route[ny][nx] = route[p.y][p.x] + map[ny][nx];	// 해당 위치까지 걸리는 시간 갱신
					visited[ny][nx] = true;
					pq.offer(new Point(ny, nx, route[p.y][p.x] + map[ny][nx]));
				}
			}
			
			System.out.printf("#%d %d\n", t+1, answer);
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