// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953_EscapeArrest {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// i번째 터널 타입이 갈 수 있는 방향을 나타낸 배열 (ex. directions[1]은 15로, 8+4+2+1이기 때문에 4방향 모두 갈 수 있다)
		int[] directions = { 0, 15, 5, 10, 3, 6, 12, 9 }; // 상:1, 우:2, 하:4, 좌:8

		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			
			// 데이터를 입력받는 부분
			st = new StringTokenizer(in.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());

			int[][] map = new int[R][C];
			boolean[][] visited = new boolean[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작점을 처리한 채로 시작
			int hour = 1;
			int cnt = 1;
			Queue<Point> queue = new LinkedList<>();
			queue.offer(new Point(startR, startC, map[startR][startC]));
			visited[startR][startC] = true;

			// BFS로 파이프 타입에 따라 갈 수 있는 경로를 탐색
			while (!queue.isEmpty()) {
				
				if (hour >= H)	// 시간 제한에 걸리는지 확인
					break;
				hour++;
				
				int size = queue.size();	// 레벨을 관리하기 위해 queue size씩만 순회
				for (int i = 0; i < size; i++) {
					Point p = queue.poll();
					int dir = directions[p.type];	// 현재 위치의 파이프 상태에 따라 갈 수 있는 방향을 받아옴

					if ((dir & 1) > 0) { // 위쪽으로 갈 수 있음
						int ny = p.y - 1;
						int nx = p.x;
						
						// 범위를 벗어나거나, 다음 위치의 파이프가 현재 위치로 뚫려있지 않거나, 이미 방문한 곳이면 패스
						if (ny >= 0 && (directions[map[ny][nx]] & 4) > 0 && !visited[ny][nx]) {
							cnt++;		// 갈 수 있는 경우의 수를 증가
							visited[ny][nx] = true;		// 방문 처리
							queue.offer(new Point(ny, nx, map[ny][nx]));	// 다음 탐색을 위해 큐에 삽입
						}
					}
					if ((dir & 2) > 0) { // 오른쪽으로 갈 수 있음
						int ny = p.y;
						int nx = p.x + 1;
						if (nx < C && (directions[map[ny][nx]] & 8) > 0 && !visited[ny][nx]) {
							cnt++;
							visited[ny][nx] = true;
							queue.offer(new Point(ny, nx, map[ny][nx]));
						}
					}
					if ((dir & 4) > 0) { // 밑쪽으로 갈 수 있음
						int ny = p.y + 1;
						int nx = p.x;
						if (ny < R && (directions[map[ny][nx]] & 1) > 0 && !visited[ny][nx]) {
							cnt++;
							visited[ny][nx] = true;
							queue.offer(new Point(ny, nx, map[ny][nx]));
						}
					}
					if ((dir & 8) > 0) { // 왼쪽으로 갈 수 있음
						int ny = p.y;
						int nx = p.x - 1;
						if (nx >= 0 && (directions[map[ny][nx]] & 2) > 0 && !visited[ny][nx]) {
							cnt++;
							visited[ny][nx] = true;
							queue.offer(new Point(ny, nx, map[ny][nx]));
						}
					}
				}
			}

			System.out.printf("#%d %d\n", t + 1, cnt);
		}
	}

	static class Point {
		int y, x, type;

		public Point(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}
}