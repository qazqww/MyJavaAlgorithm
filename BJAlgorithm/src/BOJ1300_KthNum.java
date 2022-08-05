// https://www.acmicpc.net/problem/1300

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1300_KthNum {
	
	static int N, k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		k = Integer.parseInt(in.readLine());
		
		System.out.println(binary(0, k));
	}
	
	static long binary(long min, long max) {
		if (min >= max)
			return min;
		
		long mid = (min + max) / 2;
		
		if (result(mid) >= k) {
			return binary(min, mid);
		}
		else {
			return binary(mid+1, max);
		}
	}
	
	static long result(long num) {
		long sum = 0;
		for (int i = 1; i <= N; i++)
			sum += Math.min(num / i, N);
		return sum;
	}
}