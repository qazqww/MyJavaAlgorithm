// https://www.acmicpc.net/problem/1188

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1188_FoodCritic {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		
		if (N > M)
			N %= M;
		
		int common = 1;
		int min = Math.max(N, M);
		for (int i = 2; i <= min; i++) {
			if (N % i == 0 && M % i == 0)
				common = i;
		}
		
		if (M / common == 1)
			System.out.println(0);
		else
			System.out.println((M/common - N/common) * N);
	}
}
