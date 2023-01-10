// https://www.acmicpc.net/problem/15652

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15652_NAndM4 {
	static int N, M;
	static int[] selected;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		selected = new int[M];
		permu(0, 1);
	}
	
	static void permu(int m, int min) {
		if (m == M) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb.append(selected[i] + " ");
			}
			sb.setLength(sb.length() - 1);
			System.out.println(sb);
			return;
		}
		
		for (int i = min; i <= N; i++) {
			selected[m] = i;
			permu(m + 1, i);
		}
	}
}