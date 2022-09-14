// https://www.acmicpc.net/problem/1194

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1194_MoonRising {
		
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		int R = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		int answer = Integer.MAX_VALUE;
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		char[][] map = new char[R][C];
		int curY = -1;
		int curX = -1;
		
		for (int r = 0; r < R; r++) {
			String temp2 = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = temp2.charAt(c);
				if (map[r][c] == '0') {
					curY = r;
					curX = c;
				}
			}
		}
		
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][64];
		queue.offer(new int[] { curY, curX, 1, 0 }); // step, keyNum (FEDCBA)
		visited[curY][curX][0] = true;
		
		loop: while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur[0] + dy[d];
				int nx = cur[1] + dx[d];
				int keyNum = cur[3];
				
				if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == '#')
					continue;
				
				if (map[ny][nx] >= 'A' && map[ny][nx] <= 'F') {
					if ((cur[3] & (1 << (map[ny][nx] - 'A'))) == 0) {
						continue;
					}
				}
				
				if (map[ny][nx] == '1') {
					answer = Math.min(answer, cur[2]);
					break loop;
				}
				
				if (map[ny][nx] >= 'a' && map[ny][nx] <= 'f')
					keyNum |= (1 << map[ny][nx] - 'a');
				
				if (visited[ny][nx][keyNum])
					continue;
				
				visited[ny][nx][keyNum] = true;
				queue.offer(new int[] { ny, nx, cur[2] + 1, keyNum });
			}
		}
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
}