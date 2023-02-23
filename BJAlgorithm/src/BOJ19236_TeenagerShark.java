// https://www.acmicpc.net/problem/19236

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19236_TeenagerShark {

	static int[] dy = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Fish[][] map = new Fish[4][4];
		Point[] order = new Point[17];
		
		for (int r = 0; r < 4; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < 4; c++) {
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				map[r][c] = new Fish(no, dir);
				order[no] = new Point(r, c);
			}
		}
		
		answer += map[0][0].no;
		order[map[0][0].no] = new Point(-1, -1);
		map[0][0] = new Fish(0, map[0][0].dir);
		order[0] = new Point(0, 0);
		
		eating(answer, map, order);
		System.out.println(answer);
	}
	
	static void eating(int score, Fish[][] map, Point[] order) {
		answer = Math.max(answer, score);
		
		// 물고기 이동
		for (int i = 1; i < 17; i++) {
			int y = order[i].y;
			int x = order[i].x;
			
			if (x == -1 || y == -1)
				continue;
			
			Fish fish = map[y][x];
			while (true) {
				int ny = y + dy[fish.dir];
				int nx = x + dx[fish.dir];
				
				if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || (ny == order[0].y && nx == order[0].x)) {
					fish.dir = (fish.dir + 1) % 8;
					continue;
				}
				
				swap(map, order, new Point(y, x), new Point(ny, nx));
				break;
			}
		}
		
//		System.out.println(score + " After Move");
//		print(map);
		
		// 상어 이동
		int y = order[0].y;
		int x = order[0].x;
		
		Fish shark = map[y][x];
		int ny = y;
		int nx = x;
		
		while (true) {
			ny += dy[shark.dir];
			nx += dx[shark.dir];
			
			if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) {
				break;
			}
			
			if (map[ny][nx].no == -1) {
				continue;
			}
			
			// 상어 이동 처리. 경우의 수 발생
			Fish[][] newMap = new Fish[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					newMap[i][j] = new Fish(map[i][j].no, map[i][j].dir);
				}
			}
			
			Point[] newOrder = new Point[17];
			for (int i = 0; i < 17; i++) {
				newOrder[i] = new Point(order[i].y, order[i].x);
			}

			int newScore = map[ny][nx].no;
			newOrder[map[ny][nx].no] = new Point(-1, -1);
			newMap[order[0].y][order[0].x].no = -1;
			newMap[ny][nx].no = 0;
			
			newOrder[0].y = ny;
			newOrder[0].x = nx;
			
			// 재귀
//			System.out.println(score + " After Eat : " + newScore);
//			print(map);
			
			eating(score + newScore, newMap, newOrder);
		}
	}
	
	static void print(Fish[][] map) {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				System.out.print(map[r][c] + "\t");
			}
			System.out.println();
		}
	}
	
	static void swap(Fish[][] map, Point[] order, Point p1, Point p2) {
		Fish f1 = map[p1.y][p1.x];
		Fish f2 = map[p2.y][p2.x];
		
		int tempNo = f1.no;
		int tempDir = f1.dir;
		f1.no = f2.no;
		f1.dir = f2.dir;
		f2.no = tempNo;
		f2.dir = tempDir;
		
		if (f1.no != -1)
			order[f1.no] = new Point(p1.y, p1.x);
		if (f2.no != -1)
			order[f2.no] = new Point(p2.y, p2.x);
	}
	
	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + "]";
		}
	}
	
	static class Fish {
		int no;
		int dir;
		public Fish(int no, int dir) {
			this.no = no;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Fish [no=" + no + ", dir=" + dir + "]";
		}
	}
}