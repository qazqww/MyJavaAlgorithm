// https://www.acmicpc.net/problem/1927

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1927_MinHeap {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(in.readLine());
			if (num == 0) {
				if (pq.isEmpty())
					sb.append(0 + "\n");
				else
					sb.append(pq.poll() + "\n");
			}
			else {
				pq.offer(num);
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}