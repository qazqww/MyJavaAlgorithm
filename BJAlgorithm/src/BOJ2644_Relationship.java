// https://www.acmicpc.net/problem/2644

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS 코드
public class BOJ2644_Relationship {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		
		ArrayList<Integer>[] list = new ArrayList[n];
		for (int i = 0; i < n; i++)
			list[i] = new ArrayList<>();
		boolean[] visited = new boolean[n];

		int m = Integer.parseInt(in.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			list[s].add(e);
			list[e].add(s);
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		int level = -1;
		boolean isFind = false;
		loop: while(!queue.isEmpty()) {
			int size = queue.size();
			level++;
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				if (cur == end) {
					isFind = true;
					break loop;
				}
				
				for (Integer p : list[cur]) {
					if (visited[p])
						continue;
					queue.offer(p);
					visited[p] = true;
				}
			}
		}
		
		System.out.println(isFind ? level : -1);
	}
}


/* Dijkstra 코드
public static void main(String[] args) throws IOException {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(in.readLine());
	StringTokenizer st = new StringTokenizer(in.readLine());
	int start = Integer.parseInt(st.nextToken()) - 1;
	int end = Integer.parseInt(st.nextToken()) - 1;
	
	int[][] map = new int[n][n];
	int[] dijk = new int[n];
	boolean[] visited = new boolean[n];

	int m = Integer.parseInt(in.readLine());
	for (int i = 0; i < m; i++) {
		st = new StringTokenizer(in.readLine());
		int s = Integer.parseInt(st.nextToken()) - 1;
		int e = Integer.parseInt(st.nextToken()) - 1;
		map[s][e] = 1;
		map[e][s] = 1;
	}
	
	for (int i = 0; i < n; i++) {
		dijk[i] = map[start][i];
		if (start != i && dijk[i] == 0)
			dijk[i] = 100_000;
	}
	
	int min = 0;
	int cur = 0;
	
	for (int i = 0; i < n; i++) {
		min = 100_000;
		for (int j = 0; j < n; j++) {
			if (visited[j])
				continue;
			
			if (dijk[j] < min) {
				min = dijk[j];
				cur = j;
			}
		}
		
		visited[cur] = true;
		if (cur == end)
			break;
		
		for (int j = 0; j < n; j++) {
			if (visited[j])
				continue;
			if (map[cur][j] == 0)
				continue;
			
			if (dijk[j] > min + map[cur][j])
				dijk[j] = min + map[cur][j];
		}
	}

	System.out.println(dijk[end] > 99999 ? -1 : dijk[end]);
}
*/