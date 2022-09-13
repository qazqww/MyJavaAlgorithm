// https://www.acmicpc.net/problem/1240

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1240_NodeDistance {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken());
			
			list[start].add(new int[] { end, dist });
			list[end].add(new int[] { start, dist });
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			Queue<int[]> queue = new LinkedList<>();
			boolean[] visited = new boolean[N];
			
			queue.offer(new int[] { start, 0 });
			visited[start] = true;
			
			loop: while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int num = cur[0];
				int dist = cur[1];
				
				for (int[] next : list[num]) {
					int nextNum = next[0];
					int nextDist = next[1];
					
					if (nextNum == end) {
						sb.append(dist + nextDist + "\n");
						break loop;
					}
					
					if (!visited[nextNum]) {
						queue.offer(new int[] { nextNum, dist + nextDist });
						visited[nextNum] = true;
					}
				}
			}
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}