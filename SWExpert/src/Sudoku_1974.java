// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Psz16AYEDFAUq

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sudoku_1974 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int[][] arr = new int[9][9];
			boolean isSudoku = true;
			
			// 입력부
			for (int y = 0; y < 9; y++) {
				st = new StringTokenizer(in.readLine());
				for (int x = 0; x < 9; x++) {
					arr[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 비트마스킹 활용
			// 가로, 세로줄 검증
			for (int i = 0; i < 9; i++) {
				int sumRow = (int)Math.pow(2, 9)-1;		// 111111111 생성
				int sumCol = (int)Math.pow(2, 9)-1;
				
				for (int j = 0; j < 9; j++) {
					sumRow &= ~(1 << arr[i][j]-1);		// 담긴 수만큼 << 하여 빼기
					sumCol &= ~(1 << arr[j][i]-1);
				}
				
				if (sumRow > 0 || sumCol > 0) {			// 1이 한자리라도 남아있으면 1~9 중 중복된 수가 존재
					isSudoku = false;
					break;
				}
			}
			
			// 3x3 격자 검증
			if (isSudoku) {
				for (int i = 0; i < 9; i += 3) {
					for (int j = 0; j < 9; j += 3) {
						int sum = (int)Math.pow(2, 9)-1;
						for (int y = 0; y < 3; y++) {
							for (int x = 0; x < 3; x++) {
								sum &= ~(1 << arr[y+i][x+j]-1);
							}
						}
						if (sum > 0) {
							isSudoku = false;
							break;
						}
					}
				}
			}

			System.out.printf("#%d %d\n", t+1, (isSudoku) ? 1 : 0);
		}
	}
}