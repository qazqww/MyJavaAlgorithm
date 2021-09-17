// https://www.acmicpc.net/problem/2458

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2458_OrderbyHeight {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int cnt = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				map[i][j] = 100_000;
			}
		}
		
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			map[from][to] = 1;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			boolean isConnected = true;
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 100_000 && map[j][i] >= 100_000) {	// 나보다 키가  크고 작음 모두 알 수 없으면
					isConnected = false;
					break;
				}
			}
			if (isConnected)
				answer++;
		}
		
		System.out.println(answer);
	}
}