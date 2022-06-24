import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ13460_BeadEscape2 {
	
	static int R, C, ry, rx, by, bx, answer = 11;
	static char[][] map;
	static Point red, blue, hole;
	
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
	
	static void printMap() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void dfs(int preDir, int cnt) {
		
		if (cnt >= answer)
			return;
		
		for (int d = 0; d < 4; d++) {
			if (d == preDir)
				continue;
			
			if ((d == 0 && red.y < blue.y) || (d == 1 && red.x > blue.x)
					|| (d == 2 && red.y > blue.y) || (d == 3 && red.x < blue.x)) {
				moveBead(true, d);
				moveBead(false, d);
			} else {
				moveBead(false, d);
				moveBead(true, d);
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
			
			if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == '#'
					|| map[ny][nx] == 'R' || map[ny][nx] == 'B') {
				ny -= dy[dir];
				nx -= dx[dir];
				break;
			}
			
			if (map[ny][nx] == 'O') {
				return hole;
			}
		}
		return new Point(ry, rx);
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