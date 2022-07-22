// https://www.acmicpc.net/problem/1431

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1431_SerialNum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
			if (a.length() != b.length())
				return a.length() < b.length() ? -1 : 1;
			else {
				int aSum = sum(a);
				int bSum = sum(b);
				if (aSum != bSum)
					return aSum < bSum ? -1 : 1;
				else
					return a.compareTo(b);
			}
		});
		
		for (int i = 0; i < N; i++) {
			pq.offer(in.readLine());
		}
		
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
	
	static int sum(String str) {
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			char target = str.charAt(i);
			if (target >= '0' && target <= '9')
				result += target - '0';
		}
		return result;
	}
}