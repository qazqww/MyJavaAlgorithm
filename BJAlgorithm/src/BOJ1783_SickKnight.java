// https://www.acmicpc.net/problem/1783

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1783_SickKnight {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		if (R <= 2 || C <= 6) {
			int[] dy = new int[] { 2, 1, -1, -2 };
			int[] dx = new int[] { 1, 2, 2, 1 };
			
			Queue<Point> queue = new LinkedList<>();
			queue.offer(new Point(0, 0, 1));
			
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				
				if (p.point > 4)
					continue;
				
				answer = Math.max(answer, p.point);
				
				for (int d = 0; d < 4; d++) {
					int ny = p.y + dy[d];
					int nx = p.x + dx[d];
					
					if (ny >= R || ny < 0 || nx >= C || nx < 0)
						continue;
					
					queue.offer(new Point(ny, nx, p.point + 1));
				}
			}
		}
		else {
			answer = C - 2;
		}
		
		System.out.println(answer);
	}
	
	static class Point {
		int y;
		int x;
		int point;
		public Point(int y, int x, int point) {
			this.y = y;
			this.x = x;
			this.point = point;
		}
	}
}