// https://www.acmicpc.net/problem/11660

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660_PrefixSum5 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			for (int j = 1; j < N; j++) {
				map[i][j] = map[i][j-1] + Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int sum = 0;
			
			for (int y = y1; y <= y2; y++) {
				if (x1 == 0)
					sum += map[y][x2];
				else
					sum += map[y][x2] - map[y][x1 - 1];
			}
			
			sb.append(sum + "\n");
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}