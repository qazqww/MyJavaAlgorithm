// https://www.acmicpc.net/problem/11497

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11497_JumpOverLog {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(in.readLine());
			int[] arr = new int[N];
			
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			int answer = arr[1] - arr[0];
			answer = Math.max(answer, arr[N - 1] - arr[N - 2]);
			for (int i = 0; i < N - 2; i++) {
				answer = Math.max(answer, arr[i + 2] - arr[i]);
			}
			System.out.println(answer);
		}
	}
}