// https://www.acmicpc.net/problem/10819

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10819_MaxGap {
	static int N, answer;
	static int[] nums, arr;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		nums = new int[N];
		arr = new int[N];
		selected = new boolean[N];
		answer = Integer.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		permu(0);
		System.out.println(answer);
	}
	
	static void permu(int n) {
		if (n == N) {
			int result = 0;
			for (int i = 0; i < N - 1; i++) {
				result += Math.abs(arr[i] - arr[i+1]);
			}
			answer = Math.max(answer, result);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				continue;
			}
			
			selected[i] = true;
			arr[n] = nums[i];
			permu(n+1);
			selected[i] = false;
		}
	}
}