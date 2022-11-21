// https://www.acmicpc.net/problem/1238

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1238_Party {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 1_000_000);
			map[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int time = Integer.parseInt(st.nextToken());
			
			map[start][end] = time;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, map[i][X] + map[X][i]);
		}
		
		System.out.println(max);
	}
}