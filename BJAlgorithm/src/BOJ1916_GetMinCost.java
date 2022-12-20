// https://www.acmicpc.net/problem/1916

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1916_GetMinCost {
	public static void main(String[] args) throws IOException {
		final int INF = 100_000_000;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		ArrayList<int[]>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++)
			list[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			list[s].add(new int[] { e, w });
		}
		
		st = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		for (int[] info : list[start]) {
			dist[info[0]] = Math.min(dist[info[0]], info[1]);
		}
		
		boolean[] visited = new boolean[N];
		visited[start] = true;
		
		for (int j = 0; j < N; j++) {
			int next = -1;
			int nextDist = INF;
			
			for (int i = 0; i < N; i++) {
				if (visited[i])
					continue;
				
				if (nextDist > dist[i]) {
					nextDist = dist[i];
					next = i;
				}
			}
			
			if (nextDist == INF)
				break;

			visited[next] = true;
			
			for (int[] info : list[next]) {
				if (dist[info[0]] > dist[next] + info[1])
					dist[info[0]] = dist[next] + info[1];
			}
		}
		
		System.out.println(dist[end]);
	}
}