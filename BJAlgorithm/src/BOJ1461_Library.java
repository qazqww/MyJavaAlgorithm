// https://www.acmicpc.net/problem/1461

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1461_Library {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> plus = new PriorityQueue<>((a,b) -> a > b ? -1 : 1);
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num > 0)
				plus.offer(num);
			else
				minus.offer(num);
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a > b ? -1 : 1);
		while (!plus.isEmpty()) {
			pq.offer(plus.poll());
			for (int i = 1; i < M; i++) {
				if (!plus.isEmpty()) {
					plus.poll();
				}
				else
					break;
			}
		}
		while (!minus.isEmpty()) {
			pq.offer(Math.abs(minus.poll()));
			for (int i = 1; i < M; i++) {
				if (!minus.isEmpty()) {
					minus.poll();
				}
				else
					break;
			}
		}
		
		int answer = pq.poll();
		while (!pq.isEmpty()) {
			answer += pq.poll() * 2;
		}
		
		System.out.println(answer);
	}
}