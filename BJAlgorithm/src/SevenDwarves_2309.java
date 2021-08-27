// https://www.acmicpc.net/problem/2309

import java.util.Arrays;
import java.util.Scanner;

public class SevenDwarves_2309 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] dwarves = new int[9];
		int sum = 0;
		
		for (int i = 0; i < 9; i++) {
			dwarves[i] = sc.nextInt();
			sum += dwarves[i];
		}
		
		loop: for (int i = 0; i < 8; i++) {
			for (int j = i+1; j < 9; j++) {
				if (sum - dwarves[i] - dwarves[j] == 100) {
					dwarves[i] = 100;
					dwarves[j] = 100;
					break loop;
				}
			}
		}
		
		Arrays.sort(dwarves);
		for (int i = 0; i < 7; i++)
			System.out.println(dwarves[i]);
	}
}