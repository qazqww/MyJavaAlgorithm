// https://www.acmicpc.net/problem/2606

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2606_Virus {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		List<Integer>[] list = new ArrayList[N];
		boolean[] visited = new boolean[N];
		
		for (int i = 0; i < N; i++)
			list[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			list[num1 - 1].add(num2 - 1);
			list[num2 - 1].add(num1 - 1);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			for (int i : list[num]) {
				if (!visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i])
				answer++;
		}
		
		System.out.println(answer - 1);
		
	}
}