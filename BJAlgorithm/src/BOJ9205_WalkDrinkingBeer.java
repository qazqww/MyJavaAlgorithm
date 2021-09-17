// https://www.acmicpc.net/problem/9205

import java.util.Scanner;

public class BOJ9205_WalkDrinkingBeer {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		final int INF = 10_000_000;
		
		for (int t = 0; t < T; t++) {
			int v = sc.nextInt() + 2;	// 편의점 개수 + 2(집, 공연장)

			Point[] p = new Point[v];		// 각 지점의 좌표(x, y)
			int[][] dist = new int[v][v];	// 각 지점들 간의 거리

			for (int i = 0; i < v; i++) {
				p[i] = new Point(sc.nextInt(), sc.nextInt());
				for (int j = 0; j <= i; j++) {
					int d = Math.abs(p[i].x - p[j].x) + Math.abs(p[i].y - p[j].y);
					
					if (d <= 1000)	// 맥주가 충분한 거리는 거리를 입력
						dist[i][j] = dist[j][i] = d;
					else			// 맥주가 부족한 거리는 연결되지 않도록 설정
						dist[i][j] = dist[j][i] = INF;
				}
			}

			// 플로이드 워셜 코드
			for (int k = 0; k < v; k++) {
				for (int i = 0; i < v; i++) {
					if (i == k) continue;
					for (int j = 0; j < v; j++) {
						if (j == i || j == k) continue;
						if (dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					}
				}
			}
			
			System.out.println((dist[0][v-1] >= INF) ? "sad" : "happy");
		}
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

/* 플로이드 + 재귀 코드 => 시간 초과로 실패
static int[][] dist;
static boolean[] visited;
static int v;

public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);

	int T = sc.nextInt();
	for (int t = 0; t < T; t++) {
		v = sc.nextInt() + 2;

		Point[] p = new Point[v];
		dist = new int[v][v];

		for (int i = 0; i < v; i++) {
			p[i] = new Point(sc.nextInt(), sc.nextInt());
			for (int j = 0; j <= i; j++) {
				dist[i][j] = dist[j][i] = Math.abs(p[i].x - p[j].x) + Math.abs(p[i].y - p[j].y);
			}
		}

		for (int k = 0; k < v; k++) {
			for (int i = 0; i < v; i++) {
				for (int j = 0; j < v; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		visited = new boolean[v];
		visited[0] = true;
		boolean result = find(0);
		
		System.out.println(result ? "happy" : "sad");
	}
}

static boolean find(int r) {
	if (dist[r][v-1] <= 1000)
		return true;
	
	for (int c = v-2; c >= 0; c--) {
		if (visited[c])
			continue;
		if (dist[r][c] <= 1000) {
			visited[c] = true;
			if (find(c))
				return true;
			visited[c] = false;
		}
	}
	
	return false;
}
*/