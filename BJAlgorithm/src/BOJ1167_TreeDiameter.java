// https://www.acmicpc.net/problem/1167

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1167_TreeDiameter {
	
	static int V;
	static List<int[]>[] dists;
	static boolean[] visited;
	static int farthestDist = -1;
	static int farthestNum = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		V = Integer.parseInt(in.readLine());

		dists = new ArrayList[V];
		for (int i = 0; i < V; i++)
			dists[i] = new ArrayList<>();
		
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int next = Integer.parseInt(st.nextToken()) - 1;
			while (next != -2) {
				int dist = Integer.parseInt(st.nextToken());
				dists[num].add(new int[] { next, dist });
				next = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		visited = new boolean[V];
		visited[0] = true;
		dfs(0, 0);
		
		visited = new boolean[V];
		visited[farthestNum] = true;
		dfs(farthestNum, 0);
		
		System.out.println(farthestDist);
	}
	
	static void dfs(int num, int sum) {
		if (farthestDist < sum) {
			farthestDist = sum;
			farthestNum = num;
		}
		
		for (int[] dist : dists[num]) {
			if (visited[dist[0]])
				continue;
			
			visited[dist[0]] = true;
			dfs(dist[0], sum + dist[1]);
			visited[dist[0]] = false;
		}
	}
}