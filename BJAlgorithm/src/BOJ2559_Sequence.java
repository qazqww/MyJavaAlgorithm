// https://www.acmicpc.net/problem/2559

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2559_Sequence {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		int answer = 0;
		for (int i = 0; i < K; i++) {
			sum += nums[i];
		}
		answer = sum;
		
		for (int i = K; i < N; i++) {
			sum += nums[i];
			sum -= nums[i - K];
			answer = Math.max(answer, sum);
		}
		
		System.out.println(answer);
	}
}