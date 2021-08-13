// https://www.acmicpc.net/problem/2567

// 방법을 생각해내지 못해서 어렵게 느껴졌던 문제
// 풀긴 했는데 코드를 더 압축시킬 방법은 있을 것 같다.

import java.util.Scanner;

public class Colorpaper2_2567 {

	static final int MAP_LEN = 100;
	static int[][] p = new int[MAP_LEN][MAP_LEN];	// 0 : 빈 곳, 1 : 종이
	static int[] sy = {-1, 1, 0, 0};	// 상 하 좌 우
	static int[] sx = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();
		int answer = 0;

		for (int i = 0; i < cnt; i++) {
			int inputX = sc.nextInt();
			int inputY = sc.nextInt();

			// 색종이 붙이기
			for (int y = inputY; y < inputY + 10; y++) {
				for (int x = inputX; x < inputX + 10; x++) {
					p[y][x] = 1;
				}
			}
		}
		
		for (int y = 0; y < MAP_LEN; y++) {
			for (int x = 0; x < MAP_LEN; x++) {
				if (p[y][x] != 1)	// 이미 체크한 곳이거나 비어있으면 패스
					continue;
				search(y, x);
			}
		}
		
		for (int y = 0; y < MAP_LEN; y++) {
			for (int x = 0; x < MAP_LEN; x++) {
				p[y][x] -= 3;			// 일반 변인 곳은 1번, 꼭지점은 2번 더할 수 있도록 설정해둔 3을 뺀다
				if (p[y][x] > 0) {		// 그 후 1, 2로 남은 곳만 모두 더한다
					answer += p[y][x];
				}
			}
		}
		
		System.out.println(answer);
	}
	
	// 탐색 메서드
	// 탐색한 곳은 일단 3으로 설정한 뒤, 사방 탐색 후 근처 0의 개수만큼 자신한테 더한다.
	// 따라서 4인 곳은 변, 5인 곳은 꼭지점이 된다.
	static void search(int y, int x) {
		p[y][x] = 3;
		for (int i = 0; i < 4; i++) {
			int dy = y + sy[i];
			int dx = x + sx[i];
			
			if (dy >= 0 && dx >= 0 && dy < MAP_LEN && dx < MAP_LEN) {
				if (p[dy][dx] == 0) {
					p[y][x]++;
				}
				if (p[dy][dx] == 1) {
					search(dy, dx);
				}
			} else {
				p[y][x]++;
			}
		}
	}
}