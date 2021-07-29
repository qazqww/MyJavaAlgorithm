// https://www.acmicpc.net/problem/1002

// 미완성 코드

import java.util.Scanner;

public class Turret_1002 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		
		for (int i = 0; i < count; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int r1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			int r2 = sc.nextInt();
			
			// ar = r1, br = r2
			double dist = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
			double max = Math.max(Math.max(r1, r2), dist);
			double sum = r1 + r2 + dist;
			
			if (r1 + r2 < dist || (x1 == y1 && x2 == y2 && (r1 > 0) || (r2 > 0))) {
				System.out.println("0");
			}
			else if (max * 2 == sum) {
				System.out.println("1");
			}
			else if (x1 == y1 && x2 == y2 && r1 == r2) {
				System.out.println("-1");
			}
			else {
				System.out.println("2");
			}
			
		}
		
	}
}
