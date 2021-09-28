// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWqUzj0arpkDFARG

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA7699_SujiTrip {
	
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	
	static int R, C, answer;
	static char[][] map;
	static int visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			answer = 0;
			visited = 0;
			map = new char[R][C];
			for (int r = 0; r < R; r++) {
				map[r] = in.readLine().toCharArray();
			}
			
			visited |= 1 << map[0][0] - 'A';
			dfs(0, 0, 1);
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
	
	static void dfs(int y, int x, int cnt) {
		
		if (cnt > answer)
			answer = cnt;
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny >= R || ny < 0 || nx >= C || nx < 0 || (visited & (1 << (map[ny][nx] - 'A'))) > 0)
				continue;
			
			visited |= 1 << (map[ny][nx] - 'A');
			dfs(ny, nx, cnt+1);
			visited &= ~(1 << (map[ny][nx] - 'A'));
		}
	}
}