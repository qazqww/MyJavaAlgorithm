// https://www.acmicpc.net/problem/2851

import java.util.Scanner;

public class SuperMario_2851 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] mushrooms = new int[10];
		for (int i = 0; i < 10; i++) {
			mushrooms[i] = sc.nextInt();
		}
		
		int sum = 0;
		boolean end = false;
		
		for (int i = 0; i < 10; i++) {
			sum += mushrooms[i];
			
			if (sum >= 100) {
				int preSum = sum - mushrooms[i];
				if (sum - 100 <= 100 - preSum)
					System.out.println(sum);
				else
					System.out.println(preSum);
				end = true;
				break;
			}
		}
		
		if (!end)
			System.out.println(sum);
	}
}