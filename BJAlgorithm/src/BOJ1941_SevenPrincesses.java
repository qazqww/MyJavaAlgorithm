// https://www.acmicpc.net/problem/1941

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BOJ1941_SevenPrincesses {
	
	static boolean[][] selected;
	static char[][] map = new char[5][5];
	static int result = 0;
	static int yCnt;
	static Set<Integer> set = new HashSet<>();
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			String temp = in.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = temp.charAt(j);
			}
		}

		List<Point> list;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				result = 0;
				yCnt = 0;
				selected = new boolean[5][5];
				list = new ArrayList<>();
				list.add(new Point(i, j));
				dfs(0, list);
			}
		}
		
		System.out.println(set.size());
	}
	
	static void dfs(int cnt, List<Point> list) {
		
		if (cnt >= 7) {
			set.add(result);
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			Point p = list.get(i);
			
			if (map[p.y][p.x] == 'Y')
				yCnt++;
			
			if (yCnt < 4) {
				result |= (1 << p.y * 5 + p.x);
				selected[p.y][p.x] = true;
				
				List<Point> newList = new ArrayList<>();
				newList.addAll(list);
				newList.remove(i);
				
				if (cnt < 6) {
					for (int d = 0; d < 4; d++) {
						int ny = p.y + dy[d];
						int nx = p.x + dx[d];
						
						if (ny >= 5 || ny < 0 || nx >= 5 || nx < 0 || (result & (1 << ny * 5 + nx)) >= 1)
							continue;
						
						if (newList.contains(new Point(ny, nx)))
							continue;
						
						newList.add(new Point(ny, nx));
					}
				}
				
				dfs(cnt+1, newList);
				result ^= (1 << p.y * 5 + p.x);
				selected[p.y][p.x] = false;
			}
			
			if (map[p.y][p.x] == 'Y')
				yCnt--;
		}
		
	}
	
	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public boolean equals(Object obj) {
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
}
