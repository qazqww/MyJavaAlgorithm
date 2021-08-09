// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT

import java.util.Scanner;

public class HamDiet_5215 {
	
	static int N;	// 재료의 수
	static int L;	// 제한 칼로리
	static int scoreSum = 0;
	static int kcalSum = 0;
	static int maxScore = 0;
	static int[] score;
	static int[] kcal;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			N = sc.nextInt();
			L = sc.nextInt();
			
			scoreSum = 0;
			kcalSum = 0;
			maxScore = 0;
			score = new int[N];
			kcal = new int[N];
			
			for (int i = 0; i < N; i++) {
				score[i] = sc.nextInt();
				kcal[i] = sc.nextInt();
			}
			
			powerSet(0);
			System.out.printf("#%d %d\n", t+1, maxScore);
		}
	}
	
	// 부분집합 코드
	static void powerSet(int index) {
		if (index == N) {
			if (scoreSum > maxScore) {	// 재료 담기 과정이 끝났다면 가장 높은 점수인지 확인
				maxScore = scoreSum;
			}
			return;
		}

		// 재료를 추가한 채로 다음 재료 탐색
		scoreSum += score[index];
		kcalSum += kcal[index];
		if (kcalSum <= L) {			// 칼로리 제한을 넘기면 패스
			powerSet(index + 1);
		}
		
		// 재료를 뺀 채로 다음 재료 탐색
		scoreSum -= score[index];
		kcalSum -= kcal[index];
		powerSet(index + 1);
	}
}
