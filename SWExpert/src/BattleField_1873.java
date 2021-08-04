// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BattleField_1873 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int k = 0; k < T; k++) {

			int tankX = -1;
			int tankY = -1;
			int tankDir = -1;	// 0:Up, 1:Down, 2:Left, 3:Right
			int[] tankDirX = { 0, 0, -1, 1 };
			int[] tankDirY = { -1, 1, 0, 0 };

			int H = sc.nextInt();
			int W = sc.nextInt();
			char[][] field = new char[H][W];
			sc.nextLine();

			// 필드맵 입력받기
			for (int h = 0; h < H; h++) {
				field[h] = sc.nextLine().toCharArray();

				// 탱크의 위치와 방향 구하기
				if (tankX == -1) {
					for (int w = 0; w < W; w++) {
						switch (field[h][w]) {
						case '^':
							tankY = h;
							tankX = w;
							tankDir = 0;
							break;
						case 'v':
							tankY = h;
							tankX = w;
							tankDir = 1;
							break;
						case '<':
							tankY = h;
							tankX = w;
							tankDir = 2;
							break;
						case '>':
							tankY = h;
							tankX = w;
							tankDir = 3;
							break;
						}
					}
				}
			}

			int N = sc.nextInt();
			sc.nextLine();
			char[] cmd = sc.nextLine().toCharArray();

			// 명령 수행하기
			for (int i = 0; i < N; i++) {
				switch (cmd[i]) {
				case 'U':
					if (tankY - 1 >= 0 && field[tankY-1][tankX] == '.') {
						field[tankY][tankX] = '.';
						tankY -= 1;
					}
					field[tankY][tankX] = '^';
					tankDir = 0;
					break;
				case 'D':
					if (tankY + 1 < H && field[tankY+1][tankX] == '.') {
						field[tankY][tankX] = '.';
						tankY += 1;
					}
					field[tankY][tankX] = 'v';
					tankDir = 1;
					break;
				case 'L':
					if (tankX - 1 >= 0 && field[tankY][tankX-1] == '.') {
						field[tankY][tankX] = '.';
						tankX -= 1;
					}
					field[tankY][tankX] = '<';
					tankDir = 2;
					break;
				case 'R':
					if (tankX + 1 < W && field[tankY][tankX+1] == '.') {
						field[tankY][tankX] = '.';
						tankX += 1;
					}
					field[tankY][tankX] = '>';
					tankDir = 3;
					break;
				case 'S':
					int dist = 1;
					boolean isHit = false;
					
					// 해당 방향에 dist를 곱하여 포탄이 점점 나아가도록 설정
					while (!isHit && tankY + tankDirY[tankDir] * dist >= 0 && tankY + tankDirY[tankDir] * dist < H &&
							tankX + tankDirX[tankDir] * dist >= 0 && tankX + tankDirX[tankDir] * dist < W) {
						switch (field[tankY + tankDirY[tankDir] * dist][tankX + tankDirX[tankDir] * dist]) {
						case '*':	// 벽돌과 마주칠 경우, 벽돌 파괴 후 종료
							isHit = true;
							field[tankY + tankDirY[tankDir] * dist][tankX + tankDirX[tankDir] * dist] = '.';
							break;
						case '#':	// 강철과 마주칠 경우 종료
							isHit = true;
							break;
						}
						dist++;
					}					
				}
			}
			
			System.out.printf("#%d ", k+1);
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					System.out.print(field[h][w]);
				}
				System.out.println();
			}

		}
	}
}
