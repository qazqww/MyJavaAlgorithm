// https://www.acmicpc.net/problem/2477

import java.util.Scanner;

public class Kmelon_2477 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int melon = sc.nextInt();
		int[] dirs = new int[6];
		int[] lens = new int[6];
		int big = 0;
		int small = 0;
		
		for (int i = 0; i < 6; i++) {
			dirs[i] = sc.nextInt();
			lens[i] = sc.nextInt();
		}
		
		if (dirs[0] == dirs[2] && dirs[1] == dirs[5]) {
			small = lens[0] * lens[1];
			big = lens[3] * lens[4];
		}
		else if (dirs[5] == dirs[1] && dirs[0] == dirs[4]) {
			small = lens[5] * lens[0];
			big = lens[2] * lens[3];
		}
		else if (dirs[4] == dirs[0] && dirs[5] == dirs[3]) {
			small = lens[4] * lens[5];
			big = lens[1] * lens[2];
		}
		else if (dirs[3] == dirs[5] && dirs[4] == dirs[2]) {
			small = lens[3] * lens[4];
			big = lens[0] * lens[1];
		}
		else if (dirs[2] == dirs[4] && dirs[3] == dirs[1]) {
			small = lens[2] * lens[3];
			big = lens[5] * lens[0];
		}
		else if (dirs[1] == dirs[3] && dirs[2] == dirs[0]) {
			small = lens[1] * lens[2];
			big = lens[4] * lens[5];
		}
		
		System.out.println((big - small) * melon);

	}
}