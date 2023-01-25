// https://www.acmicpc.net/problem/11000

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000_ClassroomAssign {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		PriorityQueue<int[]> infoPq = new PriorityQueue<>((a, b) -> {
			if (a[0] != b[0])
				return a[0] - b[0];
			else
				return a[1] - b[1];
		});
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			infoPq.offer(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}
		
		pq.offer(infoPq.poll()[1]);
		
		while (!infoPq.isEmpty()) {
			int[] info = infoPq.poll();
			
			if (info[0] >= pq.peek()) {
				pq.poll();
			}
			
			pq.offer(info[1]);
		}
		
		System.out.println(pq.size());
	}
}