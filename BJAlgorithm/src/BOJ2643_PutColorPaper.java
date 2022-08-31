// https://www.acmicpc.net/problem/2643

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2643_PutColorPaper {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		Paper[] papers = new Paper[N];
		int[] steps = new int[N];
		List<Integer>[] list = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			papers[i] = new Paper(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = i+1; j < N; j++) {
				if (isSmallerThan(papers[i], papers[j])) {
					list[j].add(i);
					papers[i].level++;
				}
				if (isBiggerThan(papers[i], papers[j])) {
					list[i].add(j);
					papers[j].level++;
				}
			}
		}
		
		int min = 100_000;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, papers[i].level);
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (papers[i].level == min) {
				Queue<Integer> queue = new LinkedList<>();
				for (int num : list[i]) {
					queue.offer(num);
					steps[num] = 1;
				}
				
				while (!queue.isEmpty()) {
					int num = queue.poll();
					max = Math.max(steps[num], max);
					for (int temp : list[num]) {
						if (steps[temp] < steps[num] + 1) {
							queue.offer(temp);
							steps[temp] = steps[num] + 1;
						}
					}
				}
			}
		}
		
		System.out.println(max + 1);
	}
	
	static boolean isSmallerThan(Paper a, Paper b) {
		if (Math.max(a.height, a.width) <= Math.max(b.height, b.width)
			&& Math.min(a.height, a.width) <= Math.min(b.height, b.width))
			return true;
		return false;
	}
	
	static boolean isBiggerThan(Paper a, Paper b) {
		if (Math.max(b.height, b.width) <= Math.max(a.height, a.width)
			&& Math.min(b.height, b.width) <= Math.min(a.height, a.width))
			return true;
		return false;
	}
	
	static class Paper {
		int width;
		int height;
		int level;
		
		public Paper(int width, int height) {
			this.width = width;
			this.height = height;
			this.level = 0;
		}
	}
}
