// https://www.acmicpc.net/problem/1520

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1520_DownhillRoad {
	
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int R, C;
	static int[][] map, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		dp = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			Arrays.fill(dp[r], -1);
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		System.out.println(dp[0][0]);
		
//		for (int r = 0; r < R; r++) {
//			for (int c = 0; c < C; c++) {
//				System.out.print(dp[r][c] + "\t");
//			}
//			System.out.println();
//		}
	}
	
	static int dfs(int y, int x) {
		
		if (y == R-1 && x == C-1) // 도착점 도달 : 경로 1개 추가
			return 1;
		
		if (dp[y][x] >= 0) // 이미 탐색했을 경우 : dp값 리턴
			return dp[y][x];
		
		// 탐색하지 않은 경로인 경우
		dp[y][x] = 0;
		for (int i = 0; i < 4; i++) { // 사방탐색
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 맵을 벗어나거나 내리막길이 아닌 경우는 생략
			if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[y][x] <= map[ny][nx])
				continue;
			
			dp[y][x] += dfs(ny, nx); // dfs로 탐색해가며 가능한 경로만큼 더해줌
		}
		
		return dp[y][x]; // 자신의 위치에서 탐색을 완료했으므로 dp값 리턴
		
	}
}