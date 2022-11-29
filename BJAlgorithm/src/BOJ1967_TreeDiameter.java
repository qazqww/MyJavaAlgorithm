// https://www.acmicpc.net/problem/1967

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1967_TreeDiameter {
	
	static int n, max, maxNum;
	static ArrayList<Route>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(in.readLine());
		
		if (n == 1) {
			System.out.println(0);
			return;
		}
		
		list = new ArrayList[n];
		visited = new boolean[n];
		
		for (int i = 0; i < n; i++)
			list[i] = new ArrayList<>();
		
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(in.readLine());
			int parent = Integer.parseInt(st.nextToken()) - 1;
			int child = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			list[parent].add(new Route(child, weight));
			list[child].add(new Route(parent, weight));
		}
		
		max = 0;
		maxNum = -1;
		visited[0] = true;
		dfs(0, 0);
		
		max = 0;
		visited = new boolean[n];
		visited[maxNum] = true;
		dfs(maxNum, 0);
		System.out.println(max);
	}
	
	static void dfs(int num, int sum) {
		
		if (max < sum) {
			max = sum;
			maxNum = num;
		}
		
		for (Route r : list[num]) {
			if (!visited[r.child]) {
				visited[r.child] = true;
				dfs(r.child, sum + r.weight);
				visited[r.child] = false;
			}
		}
	}
	
	static class Route {
		int child;
		int weight;
		public Route(int child, int weight) {
			this.child = child;
			this.weight = weight;
		}
	}
}