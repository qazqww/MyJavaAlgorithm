// https://www.acmicpc.net/problem/1068

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1068_Tree {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		ArrayList<Integer>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		Set<Integer> leaf = new HashSet<>();
		for (int i = 0; i < N; i++) {
			leaf.add(i);
		}
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p != -1) {
				list[p].add(i);
			}
		}
		
		int removeNode = Integer.parseInt(in.readLine());
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(removeNode);
		
		while (!queue.isEmpty()) {
			int r = queue.poll();
			for (int num : list[r]) {
				queue.offer(num);
			}
			leaf.remove(r);
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (!leaf.contains(i))
				continue;
			
			for (int num : list[i]) {
				if (num == removeNode) {
					list[i].remove((Object)num);
					break;
				}
			}
			
			if (list[i].size() == 0)
				answer++;
		}
		System.out.println(answer);
	}
}