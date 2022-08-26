// https://www.acmicpc.net/problem/1374

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1374_Classroom {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Lecture> pq = new PriorityQueue<>((a, b) -> {
			if (a.start != b.start)
				return a.start - b.start;
			return a.end - b.end;
		});
		
		PriorityQueue<Integer> endTime = new PriorityQueue<>((a, b) -> a - b);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			pq.offer(new Lecture(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		int maxNum = 1;
		
		while(!pq.isEmpty()) {
			Lecture lec = pq.poll();
			
			while (!endTime.isEmpty() && lec.start >= endTime.peek()) {
				endTime.poll();
			}
			
			endTime.offer(lec.end);
			maxNum = Math.max(maxNum, endTime.size());
		}
		
		System.out.println(maxNum);
	}
	
	static class Lecture {
		int no;
		int start;
		int end;
		public Lecture(int no, int start, int end) {
			this.no = no;
			this.start = start;
			this.end = end;
		}
	}
}
