// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PuPq6AaQDFAUq

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WhereWordIn_1979 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == 1) {
						
						// 가로 체크
						boolean possible = true;
						if (r-1 >= 0 && map[r-1][c] == 1) {
							possible = false;
						}
						else {
							for (int i = 0; i < len; i++) {
								if (r+i >= N || map[r+i][c] != 1) {
									possible = false;
									break;
								}
							}
						}
						if (possible && (r + len == N || (r + len < N && map[r+len][c] != 1)))
							answer++;
						
						// 세로 체크
						possible = true;
						if (c-1 >= 0 && map[r][c-1] == 1) {
							possible = false;
						}
						for (int i = 0; i < len; i++) {
							if (c+i >= N || map[r][c+i] != 1) {
								possible = false;
								break;
							}
						}
						
						if (possible && (c + len == N || (c + len < N && map[r][c+len] != 1)))
							answer++;
					}
				}
			}
			
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
}