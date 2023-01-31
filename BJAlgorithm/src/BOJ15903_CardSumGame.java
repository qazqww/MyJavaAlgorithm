// https://www.acmicpc.net/problem/15903

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ15903_CardSumGame {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			pq.offer((long)Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < m; i++) {
			long num1 = pq.poll();
			long num2 = pq.poll();
			pq.offer(num1 + num2);
			pq.offer(num1 + num2);
		}
		
		long answer = 0;
		while (!pq.isEmpty()) {
			answer += pq.poll();
		}
		
		System.out.println(answer);
	}
}