// https://www.acmicpc.net/problem/15681

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15681_TreeAndQuery {
	static ArrayList<Integer>[] list;
	static int[] nodeCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()) - 1;
		int Q = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		nodeCnt = new int[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(in.readLine());
			int U = Integer.parseInt(st.nextToken()) - 1;
			int V = Integer.parseInt(st.nextToken()) - 1;
			
			list[U].add(V);
			list[V].add(U);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(R);
		boolean[] visited = new boolean[N];
		visited[R] = true;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int i = list[now].size() - 1; i >= 0; i--) {
				if (visited[list[now].get(i)]) {
					list[now].remove(i);
					continue;
				}
				
				queue.offer(list[now].get(i));
				visited[list[now].get(i)] = true;
			}
		}
		
		countNode(R);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(in.readLine()) - 1;
			sb.append(nodeCnt[q] + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void countNode(int parent) {
		if (list[parent].size() == 0) {
			nodeCnt[parent] = 1;
			return;
		}
		
		int cnt = 0;
		for (int num : list[parent]) {
			countNode(num);
			cnt += nodeCnt[num];
		}
		
		nodeCnt[parent] = cnt + 1;
	}
}