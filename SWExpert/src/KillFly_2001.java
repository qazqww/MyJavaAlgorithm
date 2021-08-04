// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq&

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KillFly_2001 {

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int max = 0;
			int[][] flys = new int[N][N];

			// 파리 배열 입력받기
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					flys[y][x] = sc.nextInt();
				}
			}
			
			// 파리채로 타격 가능한 범위를 일일이 계산
			for (int y = 0; y < N - (M-1); y++) {
				for (int x = 0; x < N - (M-1); x++) {
					
					int temp = 0;
					for (int ny = y; ny < y + M; ny++) {
						for (int nx = x; nx < x + M; nx++) {
							temp += flys[ny][nx];
						}
					}
					
					if (max < temp) {
						max = temp;
					}
				}
			}
			
			System.out.printf("#%d %d\n", t+1, max);
		}
	}
}
