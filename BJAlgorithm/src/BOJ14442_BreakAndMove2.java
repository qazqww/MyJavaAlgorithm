// https://www.acmicpc.net/problem/14442

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14442_BreakAndMove2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] temp = in.readLine().split(" ");
		int R = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		int K = Integer.parseInt(temp[2]);

		int[][] map = new int[R][C];
		for (int r = 0; r < R; r++) {
			String temp2 = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = temp2.charAt(c) - '0';
			}
		}

		int[][][] info = new int[R][C][K + 1];				// 이동 거리를 저장 (K : 벽을 K번 부수고 나서의 거리)
		boolean[][][] visited = new boolean[R][C][K + 1];
		for (int k = 0; k < K; k++) {
			info[0][0][k] = 1;
			visited[0][0][k] = true;
		}
		
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 0));
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if (ny >= R || ny < 0 || nx >= C || nx < 0)
					continue;
				
				// 빈 칸일 경우
				if (map[ny][nx] == 0 && !visited[ny][nx][p.k]) {
					if (info[ny][nx][p.k] == 0 || info[p.y][p.x][p.k] + 1 < info[ny][nx][p.k]) {
						info[ny][nx][p.k] = info[p.y][p.x][p.k] + 1;
						visited[ny][nx][p.k] = true;
						queue.offer(new Point(ny, nx, p.k));
					}
				}
				// 벽을 만났을 경우 => 부술 수 있으면 부수고, k+1의 배열에 이동거리를 저장
				else if (map[ny][nx] == 1 && p.k < K && !visited[ny][nx][p.k + 1]) {
					if (info[ny][nx][p.k + 1] == 0 || info[p.y][p.x][p.k] + 1 < info[ny][nx][p.k + 1]) {
						info[ny][nx][p.k + 1] = info[p.y][p.x][p.k] + 1;
						visited[ny][nx][p.k + 1] = true;
						queue.offer(new Point(ny, nx, p.k + 1));
					}
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for (int k = 0; k < K+1; k++) {
			if (info[R-1][C-1][k] != 0)
				answer = Math.min(answer, info[R-1][C-1][k]);
		}
		
		System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
		
	}
	
	static class Point {
		int y, x, k;
		public Point(int y, int x, int k) {
			this.y = y;
			this.x = x;
			this.k = k;
		}
	}
}