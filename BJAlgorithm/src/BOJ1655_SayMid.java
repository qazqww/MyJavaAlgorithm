// https://www.acmicpc.net/problem/1655

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1655_SayMid {
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		
		PriorityQueue<Integer> minpq = new PriorityQueue<>();
		PriorityQueue<Integer> maxpq = new PriorityQueue<>((a, b) -> a < b ? 1 : -1);
		
		maxpq.offer(Integer.parseInt(in.readLine()));
		sb.append(maxpq.peek() + "\n");
		
		for (int i = 1; i < N; i++) {
			int num = Integer.parseInt(in.readLine());

			if (maxpq.peek() > num) {
				maxpq.offer(num);
			}
			else {
				minpq.offer(num);
			}
			
			if (minpq.size() > maxpq.size())
				maxpq.offer(minpq.poll());
			else if (minpq.size() < maxpq.size() - 1)
				minpq.offer(maxpq.poll());

			sb.append(maxpq.peek() + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}