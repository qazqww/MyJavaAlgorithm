// https://www.acmicpc.net/problem/1946

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1946_NewEmployee {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
			int N = Integer.parseInt(in.readLine());
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(in.readLine());
				pq.offer(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
			}
			
			int answer = 1;
			int min = pq.peek()[1];
			while (!pq.isEmpty()) {
				int[] score = pq.poll();
				if (score[1] < min) {
					min = score[1];
					answer++;
				}
			}
			System.out.println(answer);
		}
	}
}