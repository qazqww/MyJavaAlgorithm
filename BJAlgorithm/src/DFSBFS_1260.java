// https://www.acmicpc.net/problem/1260

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFSBFS_1260 {
	
	static ArrayList<Integer>[] list;	// 인접 리스트 (ArrayList에 인접 정보를 담는 N개의 배열)
	static boolean[] visited;			// 방문 여부 체크
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()) + 1;	// 0 index는 사용하지 않는다
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 인접 리스트 만들기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		
		for (int i = 1; i < N; i++) {
			Collections.sort(list[i]);	// 작은 것부터 방문하기 위해 정렬
		}
		
		dfs(start);
		System.out.println(sb);
		
		// 재활용하기 위해 다시 초기화
		visited = new boolean[N];
		sb.setLength(0);
		
		bfs(start);
		System.out.println(sb);
	}
	
	static void dfs(int current) {
		visited[current] = true;
		sb.append(current + " ");
		
		for (int i = 0; i < list[current].size(); i++) {
			if (!visited[list[current].get(i)])
				dfs(list[current].get(i));
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int current = queue.poll();
			sb.append(current + " ");
			for (int i = 0; i < list[current].size(); i++) {
				if (!visited[list[current].get(i)]) {
					queue.offer(list[current].get(i));
					visited[list[current].get(i)] = true;
				}
			}
		}
	}
}




