// https://www.acmicpc.net/problem/1600

// 비효율적으로 자료구조를 사용하여 시간복잡도가 많이 나온 코드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1660_MonkeyBeHorse {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		int[] sy = { -2, -1, 1, 2, 2, 1, -1, -2 };
		int[] sx = { 1, 2, 2, 1, -1, -2, -2, -1 };

		int K = Integer.parseInt(in.readLine()) + 1;
		StringTokenizer st = new StringTokenizer(in.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());

		int[][] map = new int[Y][X];
		int[][][] move = new int[Y][X][K];
		for (int y = 0; y < Y; y++) {
			for (int x = 0; x < X; x++) {
				for (int k = 0; k < K; k++) {
					move[y][x][k] = 100_000_000;
				}
			}
		}

		for (int k = 0; k < K; k++) {
			move[0][0][k] = 0;
		}

		for (int y = 0; y < Y; y++) {
			st = new StringTokenizer(in.readLine());
			for (int x = 0; x < X; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0));

		while (!queue.isEmpty()) {

			Point p = queue.poll();

			if (map[p.y][p.x] == 1 || (p.y == Y-1 && p.x == X-1))
				continue;

			// 원숭이로 이동할 경우
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				if (ny >= Y || ny < 0 || nx >= X || nx < 0 || map[ny][nx] == 1)
					continue;

				for (int k = 0; k < K; k++) {
					if (move[ny][nx][k] > move[p.y][p.x][k] + 1) {
						if (move[p.y][p.x][k] + 1 < move[Y-1][X-1][k]) {
							queue.offer(new Point(ny, nx));
							move[ny][nx][k] = move[p.y][p.x][k] + 1;
						}
					}
				}
			}

			// 말로 이동할 경우
			for (int i = 0; i < 8; i++) {
				int ny = p.y + sy[i];
				int nx = p.x + sx[i];
				if (ny >= Y || ny < 0 || nx >= X || nx < 0 || map[ny][nx] == 1)
					continue;

				for (int k = 0; k < K - 1; k++) {
					if (move[ny][nx][k + 1] > move[p.y][p.x][k] + 1) {
						if (move[p.y][p.x][k] + 1 < move[Y-1][X-1][k+1]) {
							queue.offer(new Point(ny, nx));
							move[ny][nx][k + 1] = move[p.y][p.x][k] + 1;
						}
					}
				}
			}
		}

		int min = 100_000_000;
		for (int k = 0; k < K; k++) {
			if (move[Y - 1][X - 1][k] < min)
				min = move[Y - 1][X - 1][k];
		}

		System.out.println((min >= 100_000_00) ? -1 : min);
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