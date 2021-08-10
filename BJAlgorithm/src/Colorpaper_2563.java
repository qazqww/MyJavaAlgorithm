// https://www.acmicpc.net/problem/2563

import java.util.Scanner;

public class Colorpaper_2563 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();
		int answer = 0;

		boolean[][] paper = new boolean[100][100];

		for (int i = 0; i < cnt; i++) {

			int inputX = sc.nextInt();
			int inputY = sc.nextInt();

			// 입력받은 위치에서 색종이 범위(10x10)만큼 처리
			for (int y = inputY; y < inputY + 10; y++) {
				for (int x = inputX; x < inputX + 10; x++) {
					paper[y][x] = true;
				}
			}
		}

		for (int y = 0; y < 100; y++) {
			for (int x = 0; x < 100; x++) {
				if (paper[y][x]) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}
