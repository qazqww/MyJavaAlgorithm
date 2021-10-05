// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656_BreakOut {
	
	static int N, C, R, total, max;
	static int[][] map;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			total = 0;	// 시작할 때 있던 벽돌의 수
			max = 0;	// 벽돌이 가장 많이 깨졌을 때의 수
			
			map = new int[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] != 0)
						total++;
				}
			}
			permu(0, map, 0);
			System.out.printf("#%d %d\n", t+1, total - max);
		}
	}
	
	static void permu(int index, int[][] map, int cnt) {
		
		if (index == N) {
			max = Math.max(max, cnt);
			return;
		}
		
		// i : 구슬을 떨어뜨릴 위치(x좌표) 
		for (int i = 0; i < C; i++) {
			
			// 맵 복사
			int[][] copyMap = new int[R][C];
			for (int r = 0; r < R; r++)
				System.arraycopy(map[r], 0, copyMap[r], 0, C);
			
			// 구슬이 깨트릴 벽돌 위치(y좌표) 구하기
			int y = -1;
			for (int r = 0; r < R; r++) {
				if (map[r][i] > 0) {
					y = r;
					break;
				}
			}
			
			// 라인에 아무것도 없는 경우
			if (y == -1) {
				permu(index+1, copyMap, cnt);
				continue;
			}
			
			// BFS로 연쇄 폭발할 벽돌 구하기
			Queue<Point> queue = new LinkedList<>();
			queue.add(new Point(y, i, copyMap[y][i]));
			copyMap[y][i] = 0;
			int destroyed = 1;
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				
				for (int pow = 0; pow < p.power; pow++) {
					for (int d = 0; d < 4; d++) {
						int ny = p.y + dy[d] * pow;
						int nx = p.x + dx[d] * pow;
						
						if (ny >= R || ny < 0 || nx >= C || nx < 0 || copyMap[ny][nx] == 0)
							continue;
						
						if (copyMap[ny][nx] > 1)
							queue.offer(new Point(ny, nx, copyMap[ny][nx]));
						
						copyMap[ny][nx] = 0;
						destroyed++;
					}
				}
			}
			
			// 벽돌 정리
			for (int c = 0; c < C; c++) {
				StringBuilder sb = new StringBuilder();
				for (int r = R-1; r >= 0; r--) {
					if (copyMap[r][c] > 0) {
						sb.append(copyMap[r][c]);
						copyMap[r][c] = 0;
					}
				}
				
				for (int r = R-1, j = 0; r >= 0 && j < sb.length(); r--, j++)
					copyMap[r][c] = sb.charAt(j) - '0';
			}
			permu(index+1, copyMap, cnt + destroyed);
		}
	}
	
	static class Point {
		int y, x, power;
		public Point(int y, int x, int p) {
			this.y = y;
			this.x = x;
			this.power = p;
		}
	}
}