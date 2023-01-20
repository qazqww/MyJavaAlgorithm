// https://www.acmicpc.net/problem/2583

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2583_GetZone {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int y = y1; y < y2; y++) {
				for (int x = x1; x < x2; x++) {
					map[y][x] = 9;
				}
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 0) {
					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(y, x));
					map[y][x] = 5;
					int cnt = 1;
					
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						for (int d = 0; d < 4; d++) {
							int ny = p.y + dy[d];
							int nx = p.x + dx[d];
							
							if (ny >= N || ny < 0 || nx >= M || nx < 0 || map[ny][nx] != 0)
								continue;
							
							map[ny][nx] = 5;
							cnt++;
							queue.offer(new Point(ny, nx));
						}
					}
					
					list.add(cnt);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Collections.sort(list);
		sb.append(list.size() + "\n");
		for (int num : list) {
			sb.append(num + " ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}