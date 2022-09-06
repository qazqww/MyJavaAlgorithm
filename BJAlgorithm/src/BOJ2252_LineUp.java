// https://www.acmicpc.net/problem/2252

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252_LineUp {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		int[] arr = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			
			list[front].add(back);
			arr[back]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i < N + 1; i++) {
			if (arr[i] == 0)
				queue.offer(i);
		}
		
		while (!queue.isEmpty()) {
			int num = queue.poll();
			
			sb.append(num + " ");
			
			for (int i : list[num]) {
				arr[i]--;
				if (arr[i] == 0)
					queue.offer(i);
			}
		}
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}