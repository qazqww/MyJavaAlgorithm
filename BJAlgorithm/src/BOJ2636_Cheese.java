// https://www.acmicpc.net/problem/2636

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636_Cheese {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };

		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];

		// 입력 부분
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int hour = 0; // 몇 시간이 지났는지
		int oldCnt = 0; // 치즈칸이 몇 개 남았는지

		while (true) {
			int cnt = 0; // 현재 시간에서의 치즈칸 개수

			// 가장자리와 이어진 공기칸을 bfs 사방탐색 체크하여
			// 만나게 되는 치즈 칸들에 부식 표시를 해둔다
			
			Queue<Point> queue = new LinkedList<>();
			map[0][0] = 9;
			queue.offer(new Point(0, 0));

			while (!queue.isEmpty()) {
				Point p = queue.poll();

				for (int i = 0; i < 4; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];

					if (ny >= R || ny < 0 || nx >= C || nx < 0)
						continue;

					if (map[ny][nx] == 1) { // 치즈 칸을 만났다 -> 공기와 접촉된 치즈 칸
						cnt++;
						map[ny][nx] = 2; // 부식될 칸은 2로 표시
					} else if (map[ny][nx] == 0) { // 지났던 공기칸은 방문체크
						map[ny][nx] = 9;
						queue.offer(new Point(ny, nx));
					}
				}
			}

			if (cnt == 0) // 남은 치즈가 없었다면 반복문 종료
				break;

			oldCnt = cnt; // 치즈가 남았다면 그 칸을 저장해둠
			hour++; // 시간 흐름 체크

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 2) // 부식될 칸을 빈 칸으로 처리
						map[r][c] = 0;
					else if (map[r][c] == 9) // 방문체크해둔 공기 칸은 원래대로
						map[r][c] = 0;
				}
			}
		}

		System.out.printf("%d\n%d", hour, oldCnt);
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