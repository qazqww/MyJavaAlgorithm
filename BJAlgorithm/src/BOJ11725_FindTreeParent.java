// https://www.acmicpc.net/problem/11725

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11725_FindTreeParent {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		boolean[] visited = new boolean[N];
		int[] parent = new int[N];
		List<Integer>[] adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(in.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			
			adjList[first].add(second);
			adjList[second].add(first);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int next : adjList[cur]) {
				if (!visited[next]) {
					parent[next] = cur;
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
		
		for (int i = 1; i < N; i++) {
			System.out.println(parent[i] + 1);
		}
	}
}