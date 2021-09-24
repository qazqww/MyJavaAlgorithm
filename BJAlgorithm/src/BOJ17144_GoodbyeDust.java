// https://www.acmicpc.net/problem/17144

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144_GoodbyeDust {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int airT = -1, airB = -1;
		
		int[][] map = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == -1) {
					airB = r;
				}
			}
		}
		airT = airB - 1;
		
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		for (int t = 0; t < T; t++) {
			
			int[][] newMap = new int[R][C];
			newMap[airT][0] = -1;
			newMap[airB][0] = -1;
			
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] > 0) {
						int dust = map[r][c];
						int moved = dust / 5;
						for (int i = 0; i < 4; i++) {
							int ny = r + dy[i];
							int nx = c + dx[i];
							if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == -1)
								continue;
							
							dust -= moved;
							newMap[ny][nx] += moved;
						}
						newMap[r][c] += dust;
					}
				}
			}
			
			for (int r = airT - 1; r > 0; r--)
				newMap[r][0] = newMap[r-1][0];
			for (int c = 0; c < C-1; c++)
				newMap[0][c] = newMap[0][c+1];
			for (int r = 0; r < airT; r++)
				newMap[r][C-1] = newMap[r+1][C-1];
			for (int c = C-1; c > 1; c--)
				newMap[airT][c] = newMap[airT][c-1];
			newMap[airT][1] = 0;
			
			for (int r = airB+1; r < R-1; r++)
				newMap[r][0] = newMap[r+1][0];
			for (int c = 0; c < C-1; c++)
				newMap[R-1][c] = newMap[R-1][c+1];
			for (int r = R-1; r > airB; r--)
				newMap[r][C-1] = newMap[r-1][C-1];
			for (int c = C-1; c > 1; c--)
				newMap[airB][c] = newMap[airB][c-1];
			newMap[airB][1] = 0;

			for (int r = 0; r < R; r++) {
				System.arraycopy(newMap[r], 0, map[r], 0, C);
			}
		}
		
		int answer = 2;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				answer += map[r][c];
			}
		}
		System.out.println(answer);
	}
}