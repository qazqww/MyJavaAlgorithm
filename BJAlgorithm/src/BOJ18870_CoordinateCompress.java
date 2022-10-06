// https://www.acmicpc.net/problem/18870

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ18870_CoordinateCompress {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			pq.offer(nums[i]);
		}
		
		int cnt = 0;
		Map<Integer, Integer> map = new HashMap<>();
		while (!pq.isEmpty()) {
			int num = pq.poll();
			if (!pq.isEmpty() && num != pq.peek()) {
				map.put(num, cnt);
				cnt++;
			}
			else if (pq.isEmpty()) {
				map.put(num, cnt);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(map.get(nums[i]) + " ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}