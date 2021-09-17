// https://www.acmicpc.net/problem/3055

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ3055_Escape {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };

		String[] temp = in.readLine().split(" ");
		int R = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		char[][] map = new char[R][C];
		
		Point D = null;	// 목적지 동굴 위치
		Queue<Point> queueS = new LinkedList<>();	// 고슴도치 위치 bfs를 위한 큐
		Queue<Point> queueW = new LinkedList<>();	// 물 위치 bfs를 위한 큐

		// 입력 부분
		for (int r = 0; r < R; r++) {
			map[r] = in.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'S')
					queueS.offer(new Point(r, c));
				else if (map[r][c] == 'D')
					D = new Point(r, c);
				else if (map[r][c] == '*')
					queueW.offer(new Point(r, c));
			}
		}

		boolean isSurvived = false;		// 탈출 성공을 체크
		int time = 0;					// 탈출까지 걸린 시간
		
		
		// 한 시간이 지날 때마다
		// 고슴도치가 갈 수 있는 위치들을 체크한 뒤
		// 물이 차는 구간들을 체크
		while (!queueS.isEmpty()) {
			time++;
			
			// 1. 고슴도치 이동 체크
			int size = queueS.size();	// 현재 단계의 고슴도치 위치만큼만 순회
			for (int i = 0; i < size; i++) {
				Point s = queueS.poll();
				
				if (map[s.y][s.x] == '*') // 고슴도치 위치였지만 물이 찬 경우 -> 고슴도치가 물에 빠짐
					continue;

				for (int j = 0; j < 4; j++) {
					int ny = s.y + dy[j];
					int nx = s.x + dx[j];

					if (ny >= R || ny < 0 || nx >= C || nx < 0)	// 맵 밖으로 나간 경우
						continue;

					if (map[ny][nx] == 'D') {	// 동굴에 도착한 경우
						isSurvived = true;
						break;
					}

					if (map[ny][nx] != '.')		// 갈 수 없는 칸인 경우
						continue;

					map[ny][nx] = 'S';					// 다음 칸에 고슴도치 이동을 체크
					queueS.offer(new Point(ny, nx));	// 큐에 삽입
				}
			}

			if (isSurvived || queueS.isEmpty())	// 고슴도치가 생존에 성공했거나 모든 경우의 고슴도치가 전멸했을 경우 반복문 종료
				break;

			// 2. 물 이동 체크
			size = queueW.size();	// 현재 단계의 물 위치만큼만 순회
			for (int i = 0; i < size; i++) {
				Point w = queueW.poll();

				for (int j = 0; j < 4; j++) {
					int ny = w.y + dy[j];
					int nx = w.x + dx[j];

					if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == 'X' || map[ny][nx] == '*'
							|| map[ny][nx] == 'D')
						continue;

					map[ny][nx] = '*';
					queueW.offer(new Point(ny, nx));
				}
			}
		}

		System.out.println(isSurvived ? time : "KAKTUS");
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