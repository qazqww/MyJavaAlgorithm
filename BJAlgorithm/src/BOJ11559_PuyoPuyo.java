// https://www.acmicpc.net/problem/11559

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559_PuyoPuyo {
	
	static final int R = 12;
	static final int C = 6;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[][] visited;
		map = new char[R][C];
		for (int i = 0; i < R; i++)
			map[i] = in.readLine().toCharArray();

		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };

		ArrayList<Point> sameList = new ArrayList<>();	// 붙어있는 같은 색들의 돌 위치를 저장 
		Queue<Point> same = new LinkedList<>();		// 같은 색을 BFS탐색하기 위한 큐
		Queue<Point> queue = new LinkedList<>();	// 같은 색이 아닌 돌들을 BFS탐색하기 위한 큐
		int cnt = 0;				// 연쇄 횟수
		boolean success = false;	// 연쇄 발생 여부
		
		while (true) {
			queue.clear();
			visited = new boolean[R][C];
			char color = 'X';
			success = false;
			
			// 가장 밑 줄에서 탐색을 시작
			if (map[R-1][0] != '.')
				queue.offer(new Point(R-1, 0, map[R-1][0]));
			
			// 돌덩이들이 서로 떨어져있을 수 있음을 대비
			for (int i = 1; i < C; i++) {
				if (map[R - 1][i - 1] == '.' && map[R - 1][i] != '.') {
					queue.offer(new Point(R - 1, i, map[R - 1][i]));
					visited[R - 1][i] = true;
				}
			}

			while (!queue.isEmpty()) {	// 더 이상 탐색할 돌이 없을 때까지 반복
				sameList.clear();
				
				Point temp = queue.poll();
				color = temp.c;		// 같은 색을 찾기 위해 색을 저장해둠
				same.offer(temp);
				sameList.add(temp);
				
				while (!same.isEmpty()) {	// 붙어있는 같은 돌이 없을 때까지 반복
					Point p = same.poll();
					visited[p.y][p.x] = true;
					for (int i = 0; i < 4; i++) {
						int ny = p.y + dy[i];
						int nx = p.x + dx[i];

						if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == '.' || visited[ny][nx])
							continue;
						
						if (color == map[ny][nx]) {
							visited[ny][nx] = true;
							same.offer(new Point(ny, nx, color));
							sameList.add(new Point(ny, nx));
						}
						else
							queue.offer(new Point(ny, nx, map[ny][nx]));
					}
				}
				
				if (sameList.size() >= 4) {		// 4개 이상 붙어있었을 경우 폭파시켜놓음
					for (Point p : sameList) {
						map[p.y][p.x] = '.';
					}
					success = true;
					sameList.clear();
				}
			}
			if (!success)
				break;

			bomb();		// 모든 폭파가 끝났으므로 맵을 정리
			cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static void bomb() {
		for (int c = 0; c < C; c++) {	// 각 세로줄 별로 처리
			StringBuilder sb = new StringBuilder();
			for (int r = R-1; r >= 0; r--) {	// 밑에서부터 올라오면서
				if (map[r][c] != '.')			// 빈 칸이 아닌 문자를
					sb.append(map[r][c]);		// StringBuilder에 저장
			}
			sb.append("............");			// 나머지 칸들은 빈 칸으로 채워둠
			
			for (int r = R-1, i=0; r >= 0; r--, i++) {
				map[r][c] = sb.charAt(i);		// 밑에서부터 다시 채움
			}
		}

//		디버깅용 출력 코드
//		for (int r = 0; r < R; r++) {
//			for (int c = 0; c < C; c++) {
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}

	static class Point {
		int y;
		int x;
		char c;
		
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		public Point(int y, int x, char c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
	}
}