// https://www.acmicpc.net/problem/1012

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1012_OrganicCabbage {
	
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };
	static int M, N, K;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			int cnt = 0;
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {					
					if (map[y][x] == 1) {
						cnt++;
						map[y][x] = 9;
						dfs(y, x);
					}
				}
			}
			
			System.out.println(cnt);
		}
		
	}
	
	static void dfs(int y, int x) {
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny >= N || ny < 0 || nx >= M || nx < 0)
				continue;
			
			if (map[ny][nx] == 1) {
				map[ny][nx] = 9;
				dfs(ny, nx);
			}
		}
	}
}
