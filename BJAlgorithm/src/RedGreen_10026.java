// https://www.acmicpc.net/problem/10026

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class RedGreen_10026 {
	
	static int N;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		char[][] map = new char[N][N];		// 일반인의 맵
		char[][] mapRG = new char[N][N];	// 색맹인의 맵
		int normalCnt = 0;
		int RGCnt = 0;
		
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			map[i] = str.toCharArray();
			mapRG[i] = str.toCharArray();
		}
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] != 'O') {		// 일반인 카운트
					normalCnt++;
					bfs(new Place(y, x), false, map);
				}
				if (mapRG[y][x] != 'O') {	// 색맹인 카운트
					RGCnt++;
					bfs(new Place(y, x), true, mapRG);
				}
			}
		}
		
		System.out.println(normalCnt + " " + RGCnt);
	}
	
	static void bfs(Place p, boolean isRG, char[][] map) {
		Queue<Place> queue = new LinkedList<>();
		char ch = map[p.y][p.x];	// 시작 위치의 알파벳 값을 받음
		map[p.y][p.x] = 'O';		// 방문한 위치는 'O'로 표시
		queue.offer(p);				// 시작 위치를 큐에 삽입
		
		while(!queue.isEmpty()) {
			Place now = queue.poll();
			
			for (int i = 0; i < 4; i++) {	// 사방으로 탐색
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				
				if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] == 'O')	// 범위 밖이거나 방문했던 곳이면 패스
					continue;
				
				if ((isRG && (map[ny][nx] == ch || map[ny][nx] + 11 == ch || map[ny][nx] - 11 == ch))	// 색맹인 경우, R과 G의 차이가 11이므로 +-11까지 모두 같은 구역으로 처리
						|| (!isRG && map[ny][nx] == ch)) {	// 일반인의 경우, 시작 위치값과 같은 경우만 같은 구역으로 본다
					map[ny][nx] = 'O';				// 방문 처리 후
					queue.offer(new Place(ny, nx));	// 큐에 삽입
				}
			}
		}
	}
	
	static class Place {
		int y;
		int x;
		public Place(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}