// https://www.acmicpc.net/problem/2210

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ2210_NumpadJump {
	
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };
	static int[][] map;
	static Set<Integer> nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		map = new int[5][5];
		nums = new HashSet<>();
		
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				dfs(i, j, 0, 0);
			}
		}
		
		System.out.println(nums.size());
	}
	
	static void dfs(int y, int x, int cnt, int num) {
		if (cnt >= 6) {
			nums.add(num);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if (ny >= 5 || ny < 0 || nx >= 5 || nx < 0)
				continue;
			
			dfs(ny, nx, cnt + 1, num * 10 + map[ny][nx]);
		}
	}
}