// https://www.acmicpc.net/problem/2660

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2660_ChairmanElection {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 10_000_000);
			map[i][i] = 0;
		}
		
		while (true) {
			st = new StringTokenizer(in.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			
			if (first == -2 && second == -2)
				break;
			
			map[first][second] = 1;
			map[second][first] = 1;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][k] + map[k][j] < map[i][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int score = -1;
			for (int j = 0; j < N; j++) {
				score = Math.max(score, map[i][j]);
			}
			min = Math.min(min, score);
			map[i][0] = score;
		}

		int candidate = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (map[i][0] == min) {
				candidate++;
				sb.append(i+1 + " ");
			}
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(min + " " + candidate);
		System.out.println(sb);
	}
}
