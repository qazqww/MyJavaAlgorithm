// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pq-OKAVYDFAUq

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumArrSpin_1961 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(in.readLine());
			int[][] map = new int[N][N];
			StringBuilder[] sb = new StringBuilder[N];
			
			for (int r = 0; r < N; r++) {
				sb[r] = new StringBuilder();
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 90도
			for (int c = 0; c < N; c++) {
				for (int r = N-1; r >= 0; r--) {
					sb[c].append(map[r][c]);
				}
			}
			
			for (int i = 0; i < N; i++) {
				sb[i].append(" ");
			}
			
			// 180도
			for (int r = N-1; r >= 0; r--) {
				for (int c = N-1; c >= 0; c--) {
					sb[N - r - 1].append(map[r][c]);
				}
			}
			
			for (int i = 0; i < N; i++) {
				sb[i].append(" ");
			}
			
			// 270도
			for (int c = N-1; c >= 0; c--) {
				for (int r = 0; r < N; r++) {
					sb[N - c - 1].append(map[r][c]);
				}
			}
			
			System.out.println("#" + (t+1));
			for (int i = 0; i < N; i++) {
				System.out.println(sb[i]);
			}
		}
	}
}