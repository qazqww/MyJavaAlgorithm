// https://www.acmicpc.net/problem/11562

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11562_BekyangBreak {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][N + 1];
		for (int i = 0; i < N+1; i++) {
			Arrays.fill(map[i], 100_000);
			map[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			map[u][v] = 0;
			if (b == 1)
				map[v][u] = 0;
			else
				map[v][u] = 1;
		}
		
		for (int k = 0; k < N+1; k++) {
			for (int i = 0; i < N+1; i++) {
				for (int j = 0; j < N+1; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(in.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(map[start][end] + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}