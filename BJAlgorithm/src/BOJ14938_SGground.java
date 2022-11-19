// https://www.acmicpc.net/problem/14938

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14938_SGground {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] items = new int[n];
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(map[i], 1_000_000);
			map[i][i] = 0;
		}
		
		st = new StringTokenizer(in.readLine());
		int idx = 0;
		while (st.hasMoreTokens()) {
			items[idx++] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			map[s][e] = Math.min(map[s][e], d);
			map[e][s] = Math.min(map[e][s], d);
		}
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < n; i++) {
			int cnt = 0;
			for (int j = 0; j < n; j++) {
				if (map[i][j] <= m) {
					cnt += items[j];
				}
			}
			answer = Math.max(answer, cnt);
		}
		
		System.out.println(answer);
	}
}