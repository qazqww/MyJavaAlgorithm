// https://www.acmicpc.net/problem/1197

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1197_MST {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1];
		for (int i = 1; i < parent.length; i++)
			parent[i] = i;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight < b.weight ? -1 : 1);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(start, end, weight));
		}
		
		int cnt = 1;
		int answer = 0;
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if (union(e.start, e.end)) {
				answer += e.weight;
				
				if (++cnt == V)
					break;
			}
		}
		
		System.out.println(answer);
	}
	
	static int findRoot(int num) {
		return parent[num] == num ? num : findRoot(parent[num]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		
		if (aRoot == bRoot)
			return false;
		
		parent[bRoot] = aRoot;
		return true;
	}
	
	static class Edge {
		int start;
		int end;
		int weight;
		
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
}