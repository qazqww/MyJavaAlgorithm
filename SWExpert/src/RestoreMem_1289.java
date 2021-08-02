// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN

import java.util.Scanner;

public class RestoreMem_1289 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int i = 0; i < T; i++) {
			String str = sc.next();
			int count = (str.charAt(0) == '1') ? 1 : 0;		// 첫 자리가 1일 경우 수정 횟수 +1
			
			for (int j = 0; j < str.length() - 1; j++) {
				if (str.charAt(j) != str.charAt(j+1)) {		// 현재 인덱스와 다음 인덱스가 다를 때마다 수정 횟수 +1
					count++;
				}
			}
			System.out.printf("#%d %d\n", i+1, count);
		}
	}
}