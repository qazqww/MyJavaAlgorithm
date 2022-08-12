// https://www.acmicpc.net/problem/1188

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1188_FoodCritic {
	
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		
		process(N, M);
		System.out.println(cnt);
		
	}
	
	static void process(int N, int M) {
		if (N > M)
			N %= M;
		
		if (N == 0)
			return;

		if (M % N == 0) {
			cnt += M - N;
		}
		else {
			cnt += M / N * N;
			process(N, M % N);
		}
	}
}
