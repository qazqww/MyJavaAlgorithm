// https://www.acmicpc.net/problem/1325

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1325_EfficientHacking {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] canHack = new int[N];
		ArrayList<Integer>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			list[B].add(A);
		}

		int max = -1;
		for (int i = 0; i < N; i++) {
			Queue<Integer> queue = new LinkedList<>();
			boolean[] visited = new boolean[N];
			
			queue.offer(i);
			visited[i] = true;
			
			int cnt = 1;
			while (!queue.isEmpty()) {
				int now = queue.poll();
				for (int num : list[now]) {
					if (visited[num])
						continue;
					
					queue.offer(num);
					visited[num] = true;
					cnt++;
				}
			}
			
			canHack[i] = cnt;
			max = Math.max(max, cnt);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if (canHack[i] == max) {
				sb.append(i+1 + " ");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}