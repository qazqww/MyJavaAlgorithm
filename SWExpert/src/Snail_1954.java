
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Snail_1954 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		int T = sc.nextInt();
		
		for (int k = 0; k < T; k++) {			
			int N = sc.nextInt();				// 달팽이의 크기
			int[][] snail = new int[N][N];		// 숫자를 담을 달팽이 배열맵
			int x = 0;							// 수를 담을 x 위치
			int y = 0;							// 수를 담을 y 위치

			/* 1부터 수를 적어나가는 방식으로 진행한다 치면
			 * 배열의 끝 혹은 이미 수가 적힌 위치를 마주할 때마다 방향 전환을 하게 됨
			 * 그 회전하는 지점들의 규칙성을 찾아 리스트에 담고
			 * 진행 시에는 리스트에 담긴 수를 마주칠 때마다 방향을 틀며
			 * 숫자를 적어가도록 진행
			 */
			
			int check = N;						// 회전하는 위치에 해당하는 수를 담을 임시 변수
			int count = 1;						// 위의 check를 구하기 위한 단계 체크
			List<Integer> turnPoint = new ArrayList<>();	// 회전하는 위치에 해당하는 수를 담을 리스트
			
			// N, N-1, N-1, N-2, N-2, ... 식으로 담음
			turnPoint.add(N);
			while (check < N * N) {
				check += N - count;
				turnPoint.add(check);
				check += N - count;
				turnPoint.add(check);
				count++;
			}
			
			// 코너를 마주칠 때마다 변할 방향을 담는 배열
			int[] dirX = { 1, 0, -1, 0 };
			int[] dirY = { 0, 1, 0, -1 };
			int dir = 0;

			// 주 실행 부분
			for (int i = 1; i <= N * N; i++) {
				snail[y][x] = i;
				if (turnPoint.contains(i)) {
					dir++;
					if (dir == 4) {
						dir = 0;
					}
				}
				y += dirY[dir];
				x += dirX[dir];
			}
			
			System.out.printf("#%d\n", k+1);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(snail[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
