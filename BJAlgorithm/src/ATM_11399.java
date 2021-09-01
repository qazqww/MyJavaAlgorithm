// https://www.acmicpc.net/problem/11399

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ATM_11399 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] times = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(times);
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			answer += times[i] * (N - i);	// 1번째 대기는 모든 사람(N)이 기다리고, 2번째 대기는 1번을 제외한 모든 사람(N-1)이 기다리고, ...
		}
		
		System.out.println(answer);
	}
}