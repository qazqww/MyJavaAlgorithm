// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT

// DP를 사용한 풀이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215_Hamburger {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			int N = Integer.parseInt(st.nextToken());		// 재료 개수
			int Kcal = Integer.parseInt(st.nextToken());	// 제한 칼로리
			
			short[][] food = new short[N][2];	// 재료의 { 점수, 칼로리 }
			
			// 재료 정보 입력 부분
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				
				food[i][0] = Short.parseShort(st.nextToken());
				food[i][1] = Short.parseShort(st.nextToken());
			}
			
			short[][] table = new short[N][Kcal+1];		// DP 기록 Table
			
			for (int n = 0; n < N; n++) {		// 0 ~ N-1 번 재료를 체크
				for (int k = 0; k <= Kcal; k++)	// 0 ~ Kcal 칼로리 별 점수를 기록
					
				if (n == 0) {
					if (k >= food[n][1]) {	// 첫 줄은 칼로리 제한에 걸리지 않을 경우 바로 점수 대입
						table[n][k] = food[n][0];
					}
				}
				
				else {
					if (k >= food[n][1]) {	// 재료를 넣을 수 있다면
						// n번째 재료를 넣지 않은 경우와, n번째 재료를 넣을 칼로리를 확보하여 넣은 경우의 점수를 비교
						table[n][k] = (short)Math.max(table[n-1][k], food[n][0] + table[n-1][k - food[n][1]]);
					}
					else {	// 재료를 넣을 수 없다면 이전 재료까지 넣었을 때의 점수를 가져옴
						table[n][k] = table[n-1][k];
					}
				}
			}
			
			System.out.printf("#%d %d\n", t+1, table[N-1][Kcal]);
		}
	}
}