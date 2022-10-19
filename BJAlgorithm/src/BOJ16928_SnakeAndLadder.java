// https://www.acmicpc.net/problem/16928

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928_SnakeAndLadder {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int ladder = Integer.parseInt(st.nextToken());
		int snake = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < ladder + snake; i++) {
			st = new StringTokenizer(in.readLine());
			map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		boolean[] visited = new boolean[101];
		visited[1] = true;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		int step = -1;
		
		loop: while (!queue.isEmpty()) {
			step++;
			int len = queue.size();
			for (int i = 0; i < len; i++) {
				int now = queue.poll();
				
				if (now > 100) {
					continue;
				}
				else if (now == 100) {
					System.out.println(step);
					break loop;
				}
				
				int next = -1;
				for (int j = 6; j > 0; j--) {
					if (map.containsKey(now + j)) {
						int place = map.get(now + j);
						if (!visited[place]) {
							visited[place] = true;
							queue.offer(place);
						}
					}
					else {
						if (now + j <= 100 && !visited[now + j]) {
							visited[now + j] = true;
							if (next == -1) {
								next = j;
								queue.offer(now + j);
							}
						}
					}
				}
			}
		}
	}
}