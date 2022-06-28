// https://www.acmicpc.net/problem/1753

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BOJ1753_ShortestPath {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		int first = Integer.parseInt(st.nextToken()) - 1;
		
		Map<Integer, Integer>[] maps = new HashMap[V];
		for (int i = 0; i < V; i++)
			maps[i] = new HashMap<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			if (s == e)
				continue;
			
			if (maps[s].containsKey(e) && maps[s].get(e) <= w)
				continue;
			
			maps[s].put(e, w);
		}
		
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[first] = 0;
		for (int key : maps[first].keySet()) {
			dist[key] = maps[first].get(key);
		}
		
		boolean[] visited = new boolean[V];
		visited[first] = true;
		
		for (int i = 0; i < V; i++) {
			int min = Integer.MAX_VALUE;
			int next = -1;
			
			for (int j = 0; j < V; j++) {
				if (!visited[j] && dist[j] != 0 && dist[j] < min) {
					min = dist[j];
					next = j;
				}
			}
			
			if (next == -1)
				break;
			
			visited[next] = true;
			for (int key : maps[next].keySet()) {
				if (!visited[key] && dist[next] + maps[next].get(key) < dist[key]) {
					dist[key] = dist[next] + maps[next].get(key);
				}
			}
		}
		
		for (int num : dist) {
			System.out.println(num == Integer.MAX_VALUE ? "INF" : num);
		}
	}
}