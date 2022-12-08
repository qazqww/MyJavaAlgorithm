// https://www.acmicpc.net/problem/2638

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638_Cheese {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		
		int[][] origin = new int[N][M];
		int cheese = 0;
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
				if (origin[i][j] == 1)
					cheese++;
			}
		}
		
		// 0. 치즈가 남아있는지 체크
		while (cheese > 0) {
			
			// 1. 맵을 복제
			int[][] dup = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					dup[i][j] = origin[i][j]; 
				}
			}
			
			// 2. 0,0 체크하여 가장자리 확인 후 수치 변경
			Queue<int[]> queue = new LinkedList<>();
			queue.offer(new int[] {0, 0});
			visited[0][0] = true;
			
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				dup[now[0]][now[1]] = 9;
				
				for (int d = 0; d < 4; d++) {
					int ny = now[0] + dy[d];
					int nx = now[1] + dx[d];
					
					if (ny >= N || ny < 0 || nx >= M || nx < 0 || visited[ny][nx] || dup[ny][nx] == 1)
						continue;
					
					queue.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
				}
			}
			
			// 3. 평범하게 맵 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (dup[i][j] == 1 && !visited[i][j]) {
						// 4. 각 칸이 바꾼 수치와 2면 이상 접촉하는지 확인 후 맞는거만 기록
						int air = 0;
						for (int d = 0; d < 4; d++) {
							int ny = i + dy[d];
							int nx = j + dx[d];
							
							if (ny >= N || ny < 0 || nx >= M || nx < 0)
								continue;
							
							if (dup[ny][nx] == 9)
								air++;
						}
						
						// 5. 기록한 치즈를 원래 맵에서 지우기
						if (air >= 2) {
							origin[i][j] = 0;
							cheese--;
						}
					}
				}
			}
			answer++;
		}
		
		System.out.println(answer);
	}
}