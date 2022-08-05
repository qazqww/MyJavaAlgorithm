// https://www.acmicpc.net/problem/1103

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1103_Game {
	
	static int R, C, max, cnt;
	static char[][] map;
	static int[][] count;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		max = -1;
		cnt = 0;
		
		map = new char[R][C];
		count = new int[R][C];
		visited = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			String row = in.readLine();
			for (int c = 0; c < C; c++) {
				if (row.charAt(c) == 'H')
					map[r][c] = 'H';
				else
					map[r][c] = row.charAt(c);
			}
		}
		
		visited[0][0] = true;
		dfs(0, 0);
		
		System.out.println(max == Integer.MAX_VALUE ? -1 : max);
	}
	
	static boolean dfs(int y, int x) {
		
		int num = map[y][x] - '0';
		
		// 위
		if (y - num >= 0 && map[y-num][x] != 'H') {
			if (visited[y-num][x]) {
				max = Integer.MAX_VALUE;
				return true;
			}
			
			if (count[y-num][x] < cnt + 1) {
				cnt++;
				visited[y-num][x] = true;
				count[y-num][x] = cnt;
				if (dfs(y - num, x))
					return true;
				cnt--;
				visited[y-num][x] = false;
			}
		}
		else {
			max = Math.max(max, cnt+1);
		}
		
		// 아래
		if (y + num < R && map[y+num][x] != 'H') {
			if (visited[y+num][x]) {
				max = Integer.MAX_VALUE;
				return true;
			}
			
			if (count[y+num][x] < cnt + 1) {
				cnt++;
				visited[y+num][x] = true;
				count[y+num][x] = cnt;
				if (dfs(y + num, x))
					return true;
				cnt--;
				visited[y+num][x] = false;
			}
		}
		else {
			max = Math.max(max, cnt+1);
		}
		
		// 왼쪽
		if (x - num >= 0 && map[y][x-num] != 'H') {
			if (visited[y][x-num]) {
				max = Integer.MAX_VALUE;
				return true;
			}
			
			if (count[y][x-num] < cnt + 1) {
				cnt++;
				visited[y][x-num] = true;
				count[y][x-num] = cnt;
				if (dfs(y, x - num))
					return true;
				cnt--;
				visited[y][x-num] = false;
			}
		}
		else {
			max = Math.max(max, cnt+1);
		}
		
		// 오른쪽
		if (x + num < C && map[y][x+num] != 'H') {
			if (visited[y][x+num]) {
				max = Integer.MAX_VALUE;
				return true;
			}
			
			if (count[y][x+num] < cnt + 1) {
				cnt++;
				visited[y][x+num] = true;
				count[y][x+num] = cnt;
				if (dfs(y, x + num))
					return true;
				cnt--;
				visited[y][x+num] = false;
			}
		}
		else {
			max = Math.max(max, cnt+1);
		}
		
		return false;
	}
}