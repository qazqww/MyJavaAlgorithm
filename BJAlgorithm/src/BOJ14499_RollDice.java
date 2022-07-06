// https://www.acmicpc.net/problem/14499

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ14499_RollDice {

	static LinkedList<Integer> row = new LinkedList<>();
	static LinkedList<Integer> col = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int[] dy = new int[] { 0, 0, 0, -1, 1 };
		int[] dx = new int[] { 0, 1, -1, 0, 0 };
		
		for (int i = 0; i < 4; i++) {
			row.add(0);
			col.add(0);
		}
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int cmdCnt = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < cmdCnt; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			
			int ny = y + dy[cmd];
			int nx = x + dx[cmd];
			
			if (ny >= R || ny < 0 || nx >= C || nx < 0)
				continue;
			
			y = ny;
			x = nx;
			
			switch(cmd) {
			case 1:
				goRight();
				break;
			case 2:
				goLeft();
				break;
			case 3:
				goUp();
				break;
			case 4:
				goDown();
				break;
			}
			
			if (map[y][x] == 0) {
				map[y][x] = row.get(3);
			}
			else {
				row.set(3, map[y][x]);
				col.set(3, map[y][x]);
				map[y][x] = 0;
			}
			
			System.out.println(row.get(1));
		}
	}
	
	static void goRight() {
		row.addFirst(row.removeLast());
		col.set(1, row.get(1));
		col.set(3, row.get(3));
	}
	
	static void goLeft() {
		row.addLast(row.removeFirst());
		col.set(1, row.get(1));
		col.set(3, row.get(3));
	}
	
	static void goUp() {
		col.addLast(col.removeFirst());
		row.set(1, col.get(1));
		row.set(3, col.get(3));
	}
	
	static void goDown() {
		col.addFirst(col.removeLast());
		row.set(1, col.get(1));
		row.set(3, col.get(3));
	}
}

/*
0 1 0 0
5 3 2 4
0 6 0 0
0 4 0 0

가로 4 1 3 6
세로 2 1 5 6

1 3 6 4
2 3 5 4

1 5 6 2
3 5 4 2
*/