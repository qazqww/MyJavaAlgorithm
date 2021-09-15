// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWyNQrCahHcDFAVP

import java.util.Scanner;

public class SWEA8382_DirChange {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 0; t < T; t++) {
			int answer = 0;
			
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			int dy = Math.abs(y2 - y1);
			int dx = Math.abs(x2 - x1);
			
			if ((dy - dx) % 2 == 0)
				answer = Math.max(dy, dx) * 2;
			else
				answer = Math.max(dy, dx) * 2 - 1;
			
			if (dy + dx == 1)
				answer = 1;
			
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
}