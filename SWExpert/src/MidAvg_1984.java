// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pw_-KAdcDFAUq

import java.util.Scanner;

public class MidAvg_1984 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int min = 10000;
			int max = 0;
			int sum = 0;
			
			for (int i = 0; i < 10; i++) {
				int num = sc.nextInt();
				min = Math.min(min, num);
				max = Math.max(max, num);
				sum += num;
			}
			
			sum -= min;
			sum -= max;
			System.out.printf("#%d %d\n", t+1, Math.round((double)sum / 8));
		}
	}
}