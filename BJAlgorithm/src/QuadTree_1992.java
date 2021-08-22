// https://www.acmicpc.net/problem/1992

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuadTree_1992 {
	
	static int N;
	static int[][] image;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		image = new int[N][N];
		for (int r = 0; r < N; r++) {
			char[] temp = in.readLine().toCharArray();
			for (int c = 0; c < N; c++) {
				image[r][c] = temp[c] - '0';
			}
		}
		
		quad(0, 0, N);
	}
	
	// 분할 탐색 코드
	static void quad(int r, int c, int len) {
		int cnt = 0;
		
		// 구역의 수를 모두 더함
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				cnt += image[i][j];
			}
		}
		
		if (cnt == len * len) {		// 모든 수가 1이면 => 1 표시
			System.out.print("1");
		} else if (cnt == 0) {
			System.out.print("0");	// 모든 수가 0이면 => 0 표시
		} else {					// 1과 0이 섞여있으면 => 한번에 나타내지 못하므로
			System.out.print("(");	// 괄호를 열고 구역을 다시 4개로 나눠 압축 시도
			quad(r, c, len/2);					// 왼쪽 위
			quad(r, c + len/2, len/2);			// 오른쪽 위
			quad(r + len/2, c, len/2);			// 왼쪽 아래
			quad(r + len/2, c + len/2, len/2);	// 오른쪽 아래 로 나누어 압축을 다시 시도
			System.out.print(")");	// 4개의 영역을 압축한 결과를 출력하고 다시 괄호로 묶는다
		}
	}
}