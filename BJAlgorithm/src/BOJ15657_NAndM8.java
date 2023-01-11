// https://www.acmicpc.net/problem/15657

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15657_NAndM8 {
	static int N, M;
	static int[] arr, selectArr;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		selectArr = new int[M];
		permu(0, 0);
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void permu(int idx, int pre) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(selectArr[i] + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
			return;
		}
		
		for (int i = pre; i < N; i++) {
			selectArr[idx] = arr[i];
			permu(idx + 1, i);
		}
	}
}