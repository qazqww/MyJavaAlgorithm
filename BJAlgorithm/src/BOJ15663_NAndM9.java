// https://www.acmicpc.net/problem/15663

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ15663_NAndM9 {
	static int N, M;
	static int[] nums;
	static boolean[] visited;
	static int[] arr;
	static Set<String> set = new HashSet<>();
	static StringBuilder answer = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		visited = new boolean[N];
		arr = new int[M];
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		combi(0);
		answer.setLength(answer.length() - 1);
		System.out.println(answer);
	}
	
	static boolean checkDupl(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + "/");
		}
		
		if (set.contains(sb.toString()))
			return false;
		
		set.add(sb.toString());
		return true;
	}
	
	static void combi(int index) {
		if (index == M) {
			if (checkDupl(arr)) {
				for (int i = 0; i < arr.length; i++) {
					answer.append(arr[i] + " ");
				}
				answer.setLength(answer.length() - 1);
				answer.append("\n");
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			
			arr[index] = nums[i];
			visited[i] = true;
			combi(index+1);
			visited[i] = false;
		}
	}
}