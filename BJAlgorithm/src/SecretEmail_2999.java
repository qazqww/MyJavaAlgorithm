// https://www.acmicpc.net/problem/2999

import java.util.Scanner;

public class SecretEmail_2999 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		int R = 0;
		int C = 0;
		for (int i = 1; i <= Math.sqrt(str.length()); i++) {	// 최대 R값 구하기
			if (str.length() % i == 0) {
				R = i;
				C = str.length() / R;
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(str.charAt(r + c * R));
			}
		}
	}
}