// https://www.acmicpc.net/problem/13460

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ13460_BeadEscape2 {
	
	static int R, C, answer = 11;
	static char[][] map;
	static Point red, blue, hole;
	static Stack<Integer> stack = new Stack<>();
	
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		
		map = new char[R][C];
		
		for (int r = 0; r < R; r++) {
			String temp2 = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = temp2.charAt(c);
				
				switch(map[r][c]) {
					case 'R':
						red = new Point(r, c);
						break;
					case 'B':
						blue = new Point(r, c);
						break;
					case 'O':
						hole = new Point(r, c);
						break;
				}
			}
		}
		
		dfs(-1, 1);
		System.out.println(answer > 10 ? -1 : answer);
	}
	
	static void dfs(int preDir, int cnt) {
		
		if (cnt >= answer)
			return;
		
		Point oldRed = new Point(red.y, red.x);
		Point oldBlue = new Point(blue.y, blue.x);
		
		for (int d = 0; d < 4; d++) {
			if (d == preDir)
				continue;
			
			stack.push(d);
			
			if ((d == 0 && red.y < blue.y) || (d == 1 && red.x > blue.x)
					|| (d == 2 && red.y > blue.y) || (d == 3 && red.x < blue.x)) {
				red = moveBead(true, d);
				blue = moveBead(false, d);
			} else {
				blue = moveBead(false, d);
				red = moveBead(true, d);
			}
			
			if (stack.size() == 6)
				System.out.println(stack + " / " + red + " , " + blue);
			
			if (red.y == oldRed.y && red.x == oldRed.x && blue.y == oldBlue.y && blue.x == oldBlue.x) {
//				System.out.println("samesame : " + stack + " / " + red + " , " + blue);
				stack.pop();
				continue;
			}
			
			if (red.equals(hole) && !blue.equals(hole)) {
				answer = Math.min(answer, cnt);
				System.out.println("*****" + answer + ":" + stack);
				System.out.println(red + " " + blue);
				stack.pop();
				return;
			}
			else if (blue.equals(hole)) {
//				System.out.println("bluehole : " + stack + " / " + red + " , " + blue);
				stack.pop();
				red = new Point(oldRed.y, oldRed.x);
				blue = new Point(oldBlue.y, oldBlue.x);
				continue;
			}
			else {
				dfs(d, cnt+1);
				red = new Point(oldRed.y, oldRed.x);
				blue = new Point(oldBlue.y, oldBlue.x);
//				if (stack.size() <= 3)
//				System.out.println(stack + " / " + red + " , " + blue);
				stack.pop();
			}
		}
		
	}
	
	static Point moveBead(boolean isRed, int dir) { // dir: 상, 우, 하, 좌
		int ny, nx;
		
		if (isRed) {
			ny = red.y;
			nx = red.x;
		}
		else {
			ny = blue.y;
			nx = blue.x;
		}
		
		while (true) {
			ny += dy[dir];
			nx += dx[dir];
			
			if (isRed && ny == blue.y && nx == blue.x) {
				if (ny == hole.y && nx == hole.x)
					return hole;
				ny -= dy[dir];
				nx -= dx[dir];
//				System.out.println("stocked : " + stack + " / " + red + " , " + blue);
				break;
			}
			else if (!isRed && ny == red.y && nx == red.x) {
				if (ny == hole.y && nx == hole.x)
					return hole;
				ny -= dy[dir];
				nx -= dx[dir];
//				System.out.println("stocked : " + stack + " / " + red + " , " + blue);
				break;
			}
			
			if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == '#') {
				ny -= dy[dir];
				nx -= dx[dir];
				break;
			}

			if (map[ny][nx] == 'O') {
				return hole;
			}
		}
		return new Point(ny, nx);
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
}
/*
8 8
########
#.####.#
#...#B##
#.##.R.#
######.#
##.##O.#
###.##.#
########
*/