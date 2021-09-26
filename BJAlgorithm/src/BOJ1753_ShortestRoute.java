// https://www.acmicpc.net/problem/1753

// 메모리 초과로 실패한 코드 (Dijkstra 구현)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1753_ShortestRoute {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(in.readLine()) - 1;
		
		int[][] map = new int[V][V];
		for (int i = 0; i < V; i++) {
			Arrays.fill(map[i], 10_000_000);
			map[i][i] = 0;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			if (map[from][to] > weight)
				map[from][to] = weight;
		}
		
		int[] weights = new int[V];
		for (int i = 0; i < V; i++)
			weights[i] = map[start][i];
		
		boolean[] visited = new boolean[V];
		visited[start] = true;
		
		for (int i = 0; i < V-1; i++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			for (int j = 0; j < weights.length; j++) {
				if (visited[j])
					continue;
				if (weights[j] < min & weights[j] != 0) {
					min = weights[j];
					minVertex = j;
				}
			}
			
			visited[minVertex] = true;
			for (int j = 0; j < weights.length; j++) {
				if (visited[j] || map[minVertex][j] == 0)
					continue;
				if (weights[j] > weights[minVertex] + map[minVertex][j])
					weights[j] = weights[minVertex] + map[minVertex][j];
			}
		}
		
		for (int i = 0; i < weights.length; i++)
			System.out.println((weights[i] >= 10_000_000) ? "INF" : weights[i]);
	}
}