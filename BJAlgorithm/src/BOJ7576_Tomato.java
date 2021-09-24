// https://www.acmicpc.net/problem/7576

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576_Tomato {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		Queue<Point> tomato = new LinkedList<>();	// 토마토 위치를 담음
		int day = -1;	// 날짜 진행
		int cnt = 0;	// 빈 칸이 아닌 위치를 체크
		
		// 입력 부분
		StringTokenizer st = new StringTokenizer(in.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0)		// 빈 칸이 아닌 위치 세기
					cnt++;
				if (map[r][c] == 1)		// 토마토가 있는 위치를 저장
					tomato.offer(new Point(r, c));
			}
		}
		
		// BFS 탐색 부분
		while(!tomato.isEmpty()) {
			int size = tomato.size();
			day++;
			for (int s = 0; s < size; s++) {	// 현재 담긴 토마토의 개수만큼만 순회 (depth-날짜 관리를 위해)
				Point p = tomato.poll();
				for (int i = 0; i < 4; i++) {	// 사방 탐색
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] != 0)	// 맵을 벗어나거나 토마토가 영향을 줄 수 없는 칸이면 패스
						continue;
					
					map[ny][nx] = 1;
					tomato.offer(new Point(ny, nx));
					cnt++;
				}
			}
		}
		
		System.out.println((cnt == R * C) ? day : -1);	// 익지 않은 토마토가 있다면 -1, 아니라면 날짜 수를 출력
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