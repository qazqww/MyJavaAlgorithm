// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0import java.util.Scanner;

import java.util.Scanner;

public class CardGame_6808 {
	
	static int[] p1 = new int[9];		// 규영이의 카드 (고정, 기준)
	static int[] p2 = new int[9];		// 인영이의 카드
	static int[] p2case = new int[9];	// 인영이 카드 순열
	static boolean[] isUsed = new boolean[9];
	static int winCount, loseCount;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 0; t < T; t++) {
			boolean[] nums = new boolean[18];	// 인영이의 카드를 골라내기 위한 배열
			winCount = 0;
			loseCount = 0;
			
			// 규영이 카드 입력받기
			for (int i = 0; i < 9; i++) {
				int num = sc.nextInt();
				nums[num-1] = true;			// 규영이의 카드는 true로
				p1[i] = num;
			}
			
			// 인영이 카드 구하기
			int index = 0;
			for (int i = 0; i < 18; i++) {
				if (!nums[i])				// false인 카드는 인영이꺼
					p2[index++] = i+1;
			}
			
			permu(0);
			System.out.printf("#%d %d %d\n", t+1, winCount, loseCount);
		}
	}
	
	static void permu(int index) {
		if (index == 9) {		// 순열 완성시 게임 시작
			int me = 0;
			int enemy = 0;
			for (int i = 0; i < 9; i++) {	// 각 순번 카드를 비교 후 승점 계산
				if (p1[i] > p2case[i])
					me += p1[i] + p2case[i];
				else if (p1[i] < p2case[i])
					enemy += p1[i] + p2case[i];
			}
			
			if (me > enemy)
				winCount++;
			else if (me < enemy)
				loseCount++;
			
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (isUsed[i])
				continue;
			p2case[index] = p2[i];
			isUsed[i] = true;
			permu(index+1);
			isUsed[i] = false;
		}
	}
}