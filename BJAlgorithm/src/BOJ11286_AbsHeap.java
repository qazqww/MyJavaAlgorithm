// https://www.acmicpc.net/problem/11286

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ11286_AbsHeap {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
			if (Math.abs(a) == Math.abs(b)) {
				return a < b ? -1 : 1;
			}
			else
				return Math.abs(a) < Math.abs(b) ? -1 : 1;
		});
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(in.readLine());
			
			if (num != 0) {
				pq.offer(num);
			}
			else {
				if (pq.isEmpty())
					System.out.println(0);
				else
					System.out.println(pq.poll());
			}
		}
	}
}