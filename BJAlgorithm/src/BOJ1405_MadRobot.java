// https://www.acmicpc.net/problem/1405

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1405_MadRobot {
	
	static boolean[][] visited;
	static int N;
	static int[] dy = new int[] { 0, 0, 1, -1 };
	static int[] dx = new int[] { 1, -1, 0, 0 };
	static double[] percent;
	static double answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		percent = new double[] { Integer.parseInt(st.nextToken()) * 0.01,  Integer.parseInt(st.nextToken()) * 0.01,
				Integer.parseInt(st.nextToken()) * 0.01, Integer.parseInt(st.nextToken()) * 0.01 }; // 동, 서, 남, 북
		
		visited = new boolean[N*2 + 1][N*2 + 1];
		visited[N][N] = true;
		dfs(N, N, 1, 0);
		System.out.println(1.0 - answer);
	}
	
	static void dfs(int y, int x, double p, int cnt) {

		if (cnt >= N) {
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			double np = p * percent[d];
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if (visited[ny][nx]) {
				answer += np;
			}
			else {
				visited[ny][nx] = true;
				dfs(ny, nx, np, cnt+1);
				visited[ny][nx] = false;
			}
		}
	}
}