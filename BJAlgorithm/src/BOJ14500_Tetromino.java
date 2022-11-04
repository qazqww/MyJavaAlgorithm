// https://www.acmicpc.net/problem/14500

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_Tetromino {
	
	static int N, M;
	static int answer = -1;
	static int[][] map;
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());;
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				find(i, j, map[i][j], 1, -10);
				bolok(i, j);
			}
		}
		
		System.out.println(answer);
	}
	
	static void find(int curY, int curX, int sum, int n, int pre) {
		if (n == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			if ((pre + 2) % 4 == d)
				continue;
			
			int ny = curY + dy[d];
			int nx = curX + dx[d];
			
			if (ny >= N || ny < 0 || nx >= M || nx < 0)
				continue;
			
			find(ny, nx, sum + map[ny][nx], n+1, d);
		}
	}
	
	static void bolok(int y, int x) {
		
		int ny = y;
		int nx = x;
		
		for (int d = 0; d < 4; d++) {
			int sum = 0;
			for (int i = 0; i < 4; i++) {
				if (i == 0) {
					ny = y;
					nx = x;
					sum += map[ny][nx];
				}
				else if (i != 3) {
					ny += dy[d];
					nx += dx[d];
					
					if (ny >= N || ny < 0 || nx >= M || nx < 0)
						continue;
					
					sum += map[ny][nx];
				}
				else {
					int sy = ny;
					int sx = nx;
					sy -= dy[d];
					sx -= dx[d];
					sy += dy[(d+1) % 4];
					sx += dx[(d+1) % 4];
					if (sy >= N || sy < 0 || sx >= M || sx < 0) { }
					else {
						sum += map[sy][sx];
						answer = Math.max(answer, sum);
						sum -= map[sy][sx];
					}
					
					sy = ny;
					sx = nx;
					sy -= dy[d];
					sx -= dx[d];
					sy += dy[(d+3) % 4];
					sx += dx[(d+3) % 4];
					if (sy >= N || sy < 0 || sx >= M || sx < 0) { }
					else {
						sum += map[sy][sx];
						answer = Math.max(answer, sum);
						sum -= map[sy][sx];
					}
				}
			}
		}
	}
}