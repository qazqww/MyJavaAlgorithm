// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf

// 최적화가 필요한 코드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA1767_ProcessorConnect {

	static int N, answer;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] map;
	static int[][] copyMap;
	static ArrayList<Point> coreList;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {

			N = Integer.parseInt(in.readLine());
			answer = Integer.MAX_VALUE;
			map = new int[N][N];
			coreList = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1)
						coreList.add(new Point(r, c));
				}
			}

			combi(0, map, 0);
			System.out.printf("#%d %d\n", t + 1, answer % 100000);
		}
	}

	static void combi(int index, int[][] map, int length) {

		if (index == coreList.size()) {
			if (length < answer)
				answer = length;
			return;
		}

		int y = coreList.get(index).y;
		int x = coreList.get(index).x;

		if (y == 0 || y == N - 1 || x == 0 || x == N - 1)
			combi(index + 1, map, length);
		else {
			for (int i = 0; i < 4; i++) {
				int ny = y;
				int nx = x;
				int len = length;
				boolean canConnect = false;

				while (true) {
					ny += dy[i];
					nx += dx[i];
					len++;

					if (ny >= N || ny < 0 || nx >= N || nx < 0) {
						canConnect = true;
						break;
					}

					if (map[ny][nx] > 0)
						break;
				}

				if (!canConnect)
					continue;

				int[][] copyMap = new int[N][N];
				for (int r = 0; r < N; r++) {
					System.arraycopy(map[r], 0, copyMap[r], 0, N);
				}

				if (ny > y) {
					for (int cy = y + 1; cy < ny; cy++)
						copyMap[cy][x] = 2;
				} else if (ny < y) {
					for (int cy = y - 1; cy > ny; cy--)
						copyMap[cy][x] = 2;
				} else if (nx > x) {
					for (int cx = x + 1; cx < nx; cx++)
						copyMap[y][cx] = 2;
				} else if (nx < x) {
					for (int cx = x - 1; cx > nx; cx--)
						copyMap[y][cx] = 2;
				}

				combi(index + 1, copyMap, len - 1);
			}
		}
		combi(index + 1, map, length + 100000);
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