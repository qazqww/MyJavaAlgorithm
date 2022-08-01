// https://www.acmicpc.net/problem/1766

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1766_Workbook {
	
	static Map<Integer, PriorityQueue<Integer>> map;
	static StringBuilder sb = new StringBuilder();
	static int[] needPre;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		needPre = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int pre = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			needPre[num]++;
			
			if (!map.containsKey(pre)) {
				map.put(pre, new PriorityQueue<>());
			}
			
			map.get(pre).offer(num);
		}
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (needPre[i] == 0)
				queue.offer(i);
		}
		
		while (!queue.isEmpty()) {
			int num = queue.poll();
			sb.append(num + " ");
			
			if (map.containsKey(num)) {
				for (int next : map.get(num)) {
					if (--needPre[next] == 0)
						queue.offer(next);
				}
			}
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
