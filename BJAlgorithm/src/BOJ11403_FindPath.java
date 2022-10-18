// https://www.acmicpc.net/problem/11403

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11403_FindPath {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][k] == 1 && map[k][j] == 1)
						map[i][j] = 1;
				}
			}
		}
		
		StringBuilder sb;
		for (int i = 0; i < N; i++) {
			sb = new StringBuilder();
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.setLength(sb.length() - 1);
			System.out.println(sb);
		}
	}
}
