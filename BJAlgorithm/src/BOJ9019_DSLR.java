// https://www.acmicpc.net/problem/9019

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019_DSLR {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[10000];
			Queue<NumInfo> queue = new LinkedList<>();
			
			visited[A] = true;
			queue.offer(new NumInfo(A, new StringBuilder()));
			
			while (!queue.isEmpty()) {
				NumInfo info = queue.poll();
				
				if (Integer.compare(info.num, B) == 0) {
					answer.append(info.sb + "\n");
					break;
				}
				
				int D = (info.num << 1) % 10000;
				int S = info.num == 0 ? 9999 : info.num - 1;
				int L = (info.num * 10) % 10000 + info.num / 1000;
				int R = (info.num / 10) + (info.num % 10) * 1000;
				
				if (!visited[D]) {
					queue.offer(new NumInfo(D, new StringBuilder(info.sb + "D")));
					visited[D] = true;
				}
				if (!visited[S]) {
					queue.offer(new NumInfo(S, new StringBuilder(info.sb + "S")));
					visited[S] = true;
				}
				if (!visited[L]) {
					queue.offer(new NumInfo(L, new StringBuilder(info.sb + "L")));
					visited[L] = true;
				}
				if (!visited[R]) {
					queue.offer(new NumInfo(R, new StringBuilder(info.sb + "R")));
					visited[R] = true;
				}
			}
		}
		
		answer.setLength(answer.length() - 1);
		System.out.println(answer);
	}
	
	static class NumInfo {
		int num;
		StringBuilder sb;
		public NumInfo(int num, StringBuilder sb) {
			this.num = num;
			this.sb = sb;
		}
	}
}