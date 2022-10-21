// https://www.acmicpc.net/problem/7662

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ7662_DoublePQ {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;
		
		PriorityQueue<Integer> ascPQ, ascDeletedPQ, descPQ, descDeletedPQ;
		for (int t = 0; t < T; t++) {
			int k = Integer.parseInt(in.readLine());
			ascPQ = new PriorityQueue<>();
			ascDeletedPQ = new PriorityQueue<>((a, b) -> b > a ? 1 : -1); 
			descPQ = new PriorityQueue<>((a, b) -> b > a ? 1 : -1);
			descDeletedPQ = new PriorityQueue<>();
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(in.readLine());
				char type = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				
				switch(type) {
				case 'I':
					ascPQ.offer(num);
					descPQ.offer(num);
					break;
				case 'D':
					if (num == 1 && !descPQ.isEmpty())
						descDeletedPQ.offer(descPQ.poll());
					else if (num == -1 && !ascPQ.isEmpty())
						ascDeletedPQ.offer(ascPQ.poll());
					break;
				}
				
				while (!ascPQ.isEmpty() && !descDeletedPQ.isEmpty() && ascPQ.peek() - descDeletedPQ.peek() == 0) {
					ascPQ.poll();
					descDeletedPQ.poll();
				}
				while (!descPQ.isEmpty() && !ascDeletedPQ.isEmpty() && descPQ.peek() - ascDeletedPQ.peek() == 0) {
					descPQ.poll();
					ascDeletedPQ.poll();
				}
			}
			
			if (ascPQ.isEmpty() || descPQ.isEmpty())
				System.out.println("EMPTY");
			else
				System.out.println(descPQ.peek() + " " + ascPQ.peek());
		}
	}
}