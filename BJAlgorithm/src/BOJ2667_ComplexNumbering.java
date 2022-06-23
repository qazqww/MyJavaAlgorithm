// https://www.acmicpc.net/problem/2667

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ2667_ComplexNumbering {
	
	static int N, cnt;
	static char[][] map;
	
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new char[N][N];
		
		for (int y = 0; y < N; y++) {
			map[y] = in.readLine().toCharArray();
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int answer = 0;
		cnt = 1;
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == '1') {
					answer++;
					map[y][x] = '0';
					cnt = 1;
					dfs(y, x);
					pq.offer(cnt);
				}
			}
		}
		
		System.out.println(answer);
		for (int i = 0; i < answer; i++)
			System.out.println(pq.poll());
	}
	
	static void dfs(int y, int x) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] == '0')
				continue;
			
			cnt++;
			map[ny][nx] = '0';
			dfs(ny, nx);
		}
	}
}