// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWGsRbk6AQIDFAVW

import java.util.Scanner;

public class Shuffle_3499 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			String[] strs = new String[N+1];
			strs[N] = "";
			for (int i = 0; i < N; i++) {
				strs[i] = sc.next();
			}
			
			int half = (strs.length) / 2;

			System.out.printf("#%d ", t+1);
			for (int i = 0; i < half; i++) {
				System.out.print(strs[i] + " " + strs[half+i] + " ");
			}
            System.out.printf("\n");
		}
	}
}
