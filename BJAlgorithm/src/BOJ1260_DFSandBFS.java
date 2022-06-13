// https://www.acmicpc.net/problem/1260

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260_DFSandBFS {
	
	static int v, e, target;
	static int[][] map;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken()) - 1;
		
		map = new int[v][v];
		
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			map[start][end] = 1;
			map[end][start] = 1;
		}
		
		// DFS
		visited = new boolean[v];
		visited[target] = true;
		dfs(target);
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
		sb.setLength(0);
		
		
		// BFS
		visited = new boolean[v];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(target);
		visited[target] = true;
		
		while (!queue.isEmpty()) {
			int n = queue.poll();
			sb.append(n+1 + " ");
			
			for (int i = 0; i < v; i++) {
				if (!visited[i] && map[n][i] > 0) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void dfs(int n) {
		sb.append(n+1 + " ");
		for (int i = 0; i < v; i++) {
			if (!visited[i] && map[n][i] > 0) {
				visited[i] = true;
				dfs(i);
			}
		}
	}
}
