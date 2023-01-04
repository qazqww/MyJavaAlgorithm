// https://www.acmicpc.net/problem/1912

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912_SerialSum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int max = Integer.MIN_VALUE;
		boolean started = false;
		int sum = 0;
		while (st.hasMoreTokens()) {
			int next = Integer.parseInt(st.nextToken());
			
			if (next <= 0 && started) {
				max = Math.max(max, sum);
			}
			
			sum += next;
			started = true;
			max = Math.max(max, sum);
			
			if (sum <= 0) {
				sum = 0;
				started = false;
			}
		}
		
		System.out.println(max);
	}
}