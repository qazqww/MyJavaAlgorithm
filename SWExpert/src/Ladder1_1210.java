// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ladder1_1210 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int[][] ladder = new int[100][100];
		int nowX = -1, nowY = -1;

		for (int i = 0; i < 10; i++) {
			sc.nextInt();

			// 사다리 맵 입력받기
			for (int y = 0; y < 100; y++) {
				for (int x = 0; x < 100; x++) {
					ladder[y][x] = sc.nextInt();
					if (ladder[y][x] == 2) {
						nowX = x;
						nowY = y;
					}
				}
			}

			while (nowY > 0) {

				// 좌우에 이동 통로가 나타나면 이동
				if (nowX + 1 < 100 && ladder[nowY][nowX + 1] == 1) {
					do {
						nowX++;
					} while (nowX + 1 < 100 && ladder[nowY][nowX + 1] == 1);
				} else if (nowX - 1 >= 0 && ladder[nowY][nowX - 1] == 1) {
					do {
						nowX--;
					} while (nowX - 1 >= 0 && ladder[nowY][nowX - 1] == 1);
				}
				
				nowY--;
			}

			System.out.printf("#%d %d\n", i+1, nowX);
		}

	}
}
