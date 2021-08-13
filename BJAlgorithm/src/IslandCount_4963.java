// https://www.acmicpc.net/problem/4963

import java.util.Scanner;

public class IslandCount_4963 {
	
	static int[][] map;
	static int xLen, yLen;
	static int[] sy = { -1, 1, 0, 0, -1, -1, 1, 1 };	// 상 하 좌 우 좌상 우상 우하 좌하
	static int[] sx = { 0, 0, -1, 1, -1, 1, 1, -1 };
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			xLen = sc.nextInt();
			yLen = sc.nextInt();
			int answer = 0;

			if (xLen == 0 && yLen == 0) // 종료 조건 체크
				break;

			map = new int[yLen][xLen];

			// 입력부
			for (int y = 0; y < yLen; y++) {
				for (int x = 0; x < xLen; x++) {
					map[y][x] = sc.nextInt();
				}
			}

			for (int y = 0; y < yLen; y++) {
				for (int x = 0; x < xLen; x++) {
					if (map[y][x] == 1) {
						search(y, x);		// 연결된 섬 찾아보기
						answer++;			// 섬 하나 추가
					}
				}
			}
			
			System.out.println(answer);
		}
	}
	
	// DFS로 탐색
	static void search(int y, int x) {
		map[y][x] = -1;
		for (int i = 0; i < 8; i++) {
			int dx = x + sx[i];
			int dy = y + sy[i];
			
			if (dx >= 0 && dy >= 0 && dx < xLen && dy < yLen && map[dy][dx] == 1) {
				search(dy, dx);
			}
		}
	}
}