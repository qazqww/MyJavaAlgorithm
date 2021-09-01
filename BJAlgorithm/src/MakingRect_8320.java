// https://www.acmicpc.net/problem/8320

import java.util.Scanner;

public class MakingRect_8320 {
	public static void main(String[] args) {
		int n = new Scanner(System.in).nextInt();
		int answer = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				if (i * j <= n)
					answer++;
				else
					break;
			}
		}
		
		System.out.println(answer);
	}
}