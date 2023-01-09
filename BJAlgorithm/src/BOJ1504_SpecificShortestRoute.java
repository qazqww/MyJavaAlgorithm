// https://www.acmicpc.net/problem/1504

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1504_SpecificShortestRoute {
	static final int INF = 1_000_000;
	static int N, E;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			map[a][b] = c;
			map[b][a] = c;
		}
		
		st = new StringTokenizer(in.readLine());
		int first = Integer.parseInt(st.nextToken()) - 1;
		int second = Integer.parseInt(st.nextToken()) - 1;
		
		int[] distFromStart = getDists(0);
		int[] distFromEnd = getDists(N - 1);
		int[] distFromDot = getDists(first);
		
		int answer = distFromDot[second] + Math.min(distFromStart[first] + distFromEnd[second],
				distFromStart[second] + distFromEnd[first]);
		
		System.out.println(answer >= INF ? -1 : answer);
	}
	
	static int[] getDists(int start) {
		int[] dist = new int[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(dist, INF);
		
		for (int i = 0; i < N; i++) {
			if (map[start][i] < INF) {
				dist[i] = map[start][i];
			}
		}
		visited[start] = true;
		
		for (int i = 0; i < N; i++) {
			int next = -1;
			int nextDist = INF;
			
			for (int j = 0; j < N; j++) {
				if (visited[j])
					continue;
				
				if (nextDist > dist[j]) {
					nextDist = dist[j];
					next = j;
				}
			}
			
			if (next == -1)
				break;
			
			visited[next] = true;
			for (int j = 0; j < N; j++) {
				if (visited[j])
					continue;
				
				if (dist[j] > dist[next] + map[next][j])
					dist[j] = dist[next] + map[next][j];
			}
		}
		
		return dist;
	}
}