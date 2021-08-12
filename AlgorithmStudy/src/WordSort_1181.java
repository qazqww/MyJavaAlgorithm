// https://www.acmicpc.net/problem/1181

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class WordSort_1181 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> {
			if (s1.length() == s2.length())		// 길이 비교
				return s1.compareTo(s2);		// 길이가 같으면 사전순 비교
			return s1.length()-s2.length();
		});
		
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			String newStr = in.readLine();
			if (!pq.contains(newStr)) {
				pq.offer(newStr);
			}
		}
		
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
}