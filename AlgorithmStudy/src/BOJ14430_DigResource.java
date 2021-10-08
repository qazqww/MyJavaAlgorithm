// https://www.acmicpc.net/problem/14430

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14430_DigResource {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];	// 자원 정보를 담음
		int[][] dp = new int[R][C];		// 해당 위치까지 얻을 수 있는 자원을 구함
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = map[0][0];	// 첫 자리는 선처리
		
		// DP 알고리즘 : 시작점에 가까운 위치부터, 왼쪽과 위쪽 중에서 큰 수를 가져와 현재 위치의 자원에 더한다
		for (int i = 1; i < R + C - 1; i++) {	// y좌표 + x좌표가 i인 위치를 처리
			for (int j = 0; j <= i; j++) {
				if (j < R && j >= 0 && i-j < C && i-j >= 0) {	// 맵의 범위를 벗어나지 않는지 확인
					if (i-j-1 < 0)		// 왼쪽이 맵 범위를 벗어날 경우 : 위쪽 값을 가져옴
						dp[j][i-j] = map[j][i-j] + dp[j-1][i-j];
					else if (j-1 < 0)	// 위쪽이 맵 범위를 벗어날 경우 : 왼쪽 값을 가져옴
						dp[j][i-j] = map[j][i-j] + dp[j][i-j-1];
					else {				// 둘 다 값이 있는 경우 : 비교해서 큰 수를 가져옴
						dp[j][i-j] = map[j][i-j] + Math.max(dp[j][i-j-1], dp[j-1][i-j]);
					}
				}
			}
		}
		
		System.out.println(dp[R-1][C-1]);	// 마지막 좌표에 있는 값이 최대값이 된다
	}
}