// https://www.acmicpc.net/problem/18405

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ18405_ContagionCompete {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		int[][] map = new int[N][N];
		PriorityQueue<Virus> pq = new PriorityQueue<>((a, b) -> {
			if (a.level == b.level)
				return a.num - b.num;
			return a.level - b.level;
		});
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if (map[y][x] > 0) {
					pq.offer(new Virus(y, x, map[y][x], 0));
				}
			}
		}
		
		st = new StringTokenizer(in.readLine());
		int S = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken()) - 1;
		int X = Integer.parseInt(st.nextToken()) - 1;
		
		if (S == 0) {
			System.out.println(map[Y][X]);
			return;
		}
		
		int time = 0;
		while (!pq.isEmpty()) {
			time++;
			int size = pq.size();
			for (int i = 0; i < size; i++) {
				Virus v = pq.poll();
				
				for (int d = 0; d < 4; d++) {
					int ny = v.y + dy[d];
					int nx = v.x + dx[d];
					
					if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] != 0)
						continue;
					
					map[ny][nx] = v.num;
					pq.offer(new Virus(ny, nx, v.num, v.level + 1));
				}
			}
			
			if (time == S)
				break;
		}
		
		System.out.println(map[Y][X]);
	}
	
	static class Virus {
		int y;
		int x;
		int num;
		int level;
		
		public Virus(int y, int x, int num, int level) {
			this.y = y;
			this.x = x;
			this.num = num;
			this.level = level;
		}
	}
}