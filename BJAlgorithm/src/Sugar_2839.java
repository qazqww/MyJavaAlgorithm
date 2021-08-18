// https://www.acmicpc.net/problem/2839

import java.util.Scanner;

public class Sugar_2839 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int target = sc.nextInt();
		int kg5 = 0;
		int answer = -1;
		
		for (kg5 = 0; kg5 <= target; kg5 += 5) { }
		
		while (true) {
			kg5 -= 5;
			
			if (kg5 < 0) {
				break;
			}
			
			if ((target - kg5) % 3 == 0) {
				answer = kg5/5 + (target - kg5) / 3;
				break;
			}
		}
		
		System.out.println(answer);
	}
}