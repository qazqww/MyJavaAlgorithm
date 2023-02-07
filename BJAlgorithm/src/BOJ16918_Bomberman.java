// https://www.acmicpc.net/problem/16918

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16918_Bomberman {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		if (N > 2 && N % 2 == 0) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append("O");
				}
				sb.append("\n");
			}
			sb.setLength(sb.length() - 1);
			System.out.println(sb);
		}
		else {
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		int[][] map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String temp = in.readLine();
			for (int j = 0; j < C; j++) {
				if (temp.charAt(j) == 'O') {
					map[i][j] = 0;
				}
				else {
					map[i][j] = -1;
				}
			}
		}
		
		for (int t = 2; t <= N; t++) {
			if (t % 2 == 0) {
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						if (map[r][c] < 0) {
							map[r][c] = t;
						}
					}
				}
			}
			else {
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						if (map[r][c] == t - 3) {
							for (int d = 0; d < 4; d++) {
								int ny = r + dy[d];
								int nx = c + dx[d];
								
								if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == map[r][c])
									continue;
								
								map[ny][nx] = -1;
								
							}
							map[r][c] = -1;
						}
					}
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] < 0) {
					sb.append('.');
				}
				else {
					sb.append('O');
				}
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
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