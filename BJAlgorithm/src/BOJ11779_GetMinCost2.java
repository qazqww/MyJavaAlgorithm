// https://www.acmicpc.net/problem/11779

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11779_GetMinCost2 {
	static final int INF = 100_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int city = Integer.parseInt(in.readLine());
		int bus = Integer.parseInt(in.readLine());
		
		int[][] map = new int[city][city];
		for (int i = 0; i < city; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}
		
		for (int i = 0; i < bus; i++) {
			st = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken());
			
			map[s][e] = Math.min(map[s][e], v);
		}
		
		st = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		
		StringBuilder[] route = new StringBuilder[city];
		int[] dist = new int[city];
		for (int i = 0; i < city; i++) {
			route[i] = new StringBuilder();
			route[i].append((start + 1) + " " + (i + 1));
			dist[i] = map[start][i];
		}
		boolean[] visited = new boolean[city];
		visited[start] = true;
		
		for (int i = 0; i < city; i++) {
			int minValue = INF;
			int minTarget = -1;
			
			for (int j = 0; j < city; j++) {
				if (visited[j])
					continue;
				
				if (minValue > dist[j]) {
					minValue = dist[j];
					minTarget = j;
				}
			}
			
			if (minTarget == -1)
				break;
			
			visited[minTarget] = true;
			
			for (int j = 0; j < city; j++) {
				if (dist[j] > dist[minTarget] + map[minTarget][j]) {
					dist[j] = dist[minTarget] + map[minTarget][j];
					route[j] = new StringBuilder(route[minTarget] + " " + (j + 1));
				}
			}
			
			if (minTarget == end)
				break;
		}

		int cnt = 1;
		for (int i = 0; i < route[end].length(); i++) {
			if (route[end].charAt(i) == ' ') {
				cnt++;
			}
		}
		
		System.out.println(dist[end] + "\n" + cnt + "\n" + route[end]);
	}
}