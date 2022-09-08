// https://www.acmicpc.net/problem/14567

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14567_Prerequisite {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] counts = new int[N];
		int[] result = new int[N];
		ArrayList<Integer>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++)
			list[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
					
			list[first].add(second);
			counts[second]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (counts[i] == 0)
				queue.offer(i);
		}

		int semester = 0;
		while (!queue.isEmpty()) {
			semester++;
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				result[cur] = semester;
				
				for (int num : list[cur]) {
					counts[num]--;
					if (counts[num] == 0)
						queue.offer(num);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int num : result)
			sb.append(num + " ");
		
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
