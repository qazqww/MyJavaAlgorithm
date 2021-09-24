// https://www.acmicpc.net/problem/1261

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1261_Algospot {
	
	static int R, C;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력 부분
		String[] temp = in.readLine().split(" ");
		C = Integer.parseInt(temp[0]);
		R = Integer.parseInt(temp[1]);
		int level = 0;
		
		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			String temp2 = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = temp2.charAt(c) - '0';
			}
		}
		
		// 출발점과 도착점이 같은 경우
		if (R == 1 && C == 1)
			System.out.println(0);
		
		// 일반적인 경우
		else {
			while (!bfs())	// 도착점에 도착하여 true를 반환할 때까지 반복
				level++;
		
			System.out.println(level);
		}
	}
	
	static boolean bfs() {
		boolean[][] visited = new boolean[R][C];	// 방문체크
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		Queue<Point> zero = new LinkedList<>();		// 갈 수 있는 위치를 bfs탐색하기 위한 큐
		zero.offer(new Point(0, 0));	// 시작점 집어넣기
		visited[0][0] = true;
		
		while (!zero.isEmpty()) {
			Point p = zero.poll();
			
			if (p.y == R - 1 && p.x == C - 1)	// 도착점에 도달했을 경우 true 반환
				return true;
			
			for (int i = 0; i < 4; i++) {	// 사방 탐색
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx])	// 맵을 벗어나거나 이미 방문했던 곳일 경우 패스
					continue;
				
				if (map[ny][nx] == 0) {			// 갈 수 있는 곳일 경우, 큐에 넣어둠
					visited[ny][nx] = true;
					zero.offer(new Point(ny, nx));
				}
				else if (map[ny][nx] == 1) {	// 빈 방과 붙어있는 벽일 경우, 따로 체크
					visited[ny][nx] = true;
					map[ny][nx] = 2;
				}
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 2)		// 빈 방과 붙어있던 벽을 모두 부수고 빈 방으로 만든다
					map[r][c] = 0;
			}
		}
		
		// 벽에서 빈 방으로 바뀐 점들을 다음 반복문에서의 출발점으로 만들면 더 최적화할 수 있을 것 같음 
		
		return false;	// 도착점에 도달하지 못했을 경우 false 반환
	}
	
	// 위치 정보 클래스
	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}