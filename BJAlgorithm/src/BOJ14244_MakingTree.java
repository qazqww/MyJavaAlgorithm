// https://www.acmicpc.net/problem/14244

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14244_MakingTree {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] parent = new int[n];
		for (int i = 1; i < n; i++) {
			parent[i] = i - 1;
		}
		
		for (int i = 0; i < m - 2; i++) {
			parent[n - 1 - i] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n; i++) {
			sb.append(parent[i] + " " + i + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}