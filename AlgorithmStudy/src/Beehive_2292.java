// https://www.acmicpc.net/problem/2292

import java.util.Scanner;

public class Beehive_2292 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		N -= 1;
		if (N == 0)
			System.out.println(1);
		else {
			int cnt = 0;
			while (N > 0) {
				N -= cnt * 6;
				cnt++;
			}
			
			System.out.println(cnt);
		}
	}
}