// https://www.acmicpc.net/problem/2230

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ2230_NumChoice {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		
		Queue<Integer> queue = new LinkedList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(in.readLine()));
		}
		
		int gap = Integer.MAX_VALUE;
		
		loop: while(!pq.isEmpty()) {
			if (queue.isEmpty()) {
				queue.offer(pq.poll());
			}
			else {
				if (pq.peek() - queue.peek() < M) {
					queue.offer(pq.poll());
				}
				else {
					while (!queue.isEmpty() && pq.peek() - queue.peek() >= M) {
						gap = Math.min(gap, pq.peek() - queue.peek());
						if (gap == M)
							break loop;
						
						queue.poll();
					}
				}
			}
		}
		
		System.out.println(gap);
	}
}