// https://www.acmicpc.net/problem/1669

// unsolved

import java.util.Scanner;

public class BOJ1669_PatADog {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int gap = -sc.nextInt() + sc.nextInt();

		int i = 1;
		int day = 0;
		while (gap > 0) {
			day++;
			gap -= i;

			if (gap <= 0)
				break;

			day++;
			gap -= i++;
		}

		System.out.println(day);
	}
}