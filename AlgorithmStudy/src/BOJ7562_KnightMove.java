// https://www.acmicpc.net/problem/7562

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ7562_KnightMove {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };	// 왼쪽 위부터 시계방향으로 8방향 탐색
		int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
		
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int len = sc.nextInt();
			
			boolean[][] map = new boolean[len][len];
			
			int y = sc.nextInt();	// 시작점
			int x = sc.nextInt();
			
			int ty = sc.nextInt();	// 도착점
			int tx = sc.nextInt();
			
			int answer = 0;
			
			if (y == ty && x == tx) {	// 시작점과 도착점이 같으면 바로 종료
				System.out.println(0);
				continue;
			}
			
			Queue<Place> queue = new LinkedList<>();	// BFS 탐색을 위한 큐 생성
			queue.offer(new Place(y, x, 0));	// 시작점 넣기
			map[y][x] = true;
			
			loop: while(!queue.isEmpty()) {
				Place p = queue.poll();
				
				for (int i = 0; i < 8; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if (ny < 0 || ny >= len || nx < 0 || nx >= len || map[ny][nx])	// 영역 밖이거나 이미 탐색했을 시
						continue;
					
					if (ny == ty && nx == tx) {		// 도착점에 도달했을 시
						answer = p.level + 1;
						break loop;
					}
					
					map[ny][nx] = true;
					queue.offer(new Place(ny, nx, p.level + 1));
				}
			}
			
			System.out.println(answer);
		}
	}
	
	static class Place {
		int x;
		int y;
		int level;
		
		public Place(int y, int x, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}
	}
}