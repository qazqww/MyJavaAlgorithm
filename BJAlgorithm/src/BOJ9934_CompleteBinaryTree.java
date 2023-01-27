// https://www.acmicpc.net/problem/9934

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ9934_CompleteBinaryTree {
	static int K, cnt;
	static int[] arr;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(in.readLine());
		cnt = (int)Math.pow(2, K) - 1;
		arr = new int[cnt];
		list = new ArrayList[K];
		for (int i = 0; i < K; i++) {
			list[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < cnt; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		search(0, 0, cnt);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			for (int num : list[i]) {
				sb.append(num + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void search(int level, int low, int high) {
		if (level >= K)
			return;
		
		int mid = (low + high) / 2;
		list[level].add(arr[mid]);
		
		search(level + 1, low, mid);
		search(level + 1, mid, high);
	}
}