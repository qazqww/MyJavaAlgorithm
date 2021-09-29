// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 자신의 키가 몇 번째인지 알기 위해서는
// 모든 정점을 자식 혹은 부모로 가져야 하므로
// 모든 정점 간의 경로를 탐색하는 플로이드-와샬 알고리즘을 사용

public class SWEA5643_OrderbyHeight {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());
			
			int[][] map = new int[N][N];
			for (int r = 0; r < N; r++) {
				Arrays.fill(map[r], 100_000);
				map[r][r] = 0;
			}
			
			for (int i = 0; i < M ; i++) {
				st = new StringTokenizer(in.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				map[from][to] = 1;
			}
			
			// 플로이드 와샬 알고리즘
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] > map[i][k] + map[k][j])
							map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
			
			int answer = 0;
			for (int i = 0; i < N; i++) {
				boolean isCorrect = true;
				for (int j = 0; j < N; j++) {
					// 자신에서(i)부터 특정 정점(j)으로 갈 수도 없고 특정 정점에서 자신으로 올 수도 없다면
					// 연결되지 않았으므로 키를 비교할 수 없다
					if (map[i][j] >= 100_000 && map[j][i] >= 100_000) {
						isCorrect = false;
						break;
					}
				}
				if (isCorrect)	// 키를 비교할 수 있는 경우만 센다
					answer++;
			}
			
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
}