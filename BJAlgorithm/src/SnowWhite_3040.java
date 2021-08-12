// https://www.acmicpc.net/problem/3040

import java.util.Scanner;

public class SnowWhite_3040 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] dwarves = new int[9];
		for (int i = 0; i < 9; i++) {
			dwarves[i] = sc.nextInt();
		}
		
		int liar1 = -1;
		int liar2 = -1;
		
		// 2명씩 조합으로 뽑아내 범인으로 가정
		loop: for (liar1 = 0; liar1 < 8; liar1++) {
			for (liar2 = liar1+1; liar2 < 9; liar2++) {	
				int sum = 0;
				
				for (int i = 0; i < 9; i++) {
					if (i == liar1 || i == liar2)	// 범인으로 가정한 난쟁이는 숫자 계산 패스
						continue;
					
					sum += dwarves[i];
				}
				
				if (sum == 100) {	// 모두 더해서 100이면 범인을 그대로 둔 채로 반복문 종료
					break loop;
				}
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (i == liar1 || i == liar2)
				continue;
			
			System.out.println(dwarves[i]);
		}
	}
}