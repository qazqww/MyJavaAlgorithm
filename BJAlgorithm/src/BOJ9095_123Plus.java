// https://www.acmicpc.net/problem/9095

import java.util.Arrays;
import java.util.Scanner;

public class BOJ9095_123Plus {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		int[] method = new int[12];
		method[1] = 1;
		method[2] = 2;
		method[3] = 4;
		
		for (int i = 4; i < 12; i++)
			method[i] = method[i-3] + method[i-2] + method[i-1];
		
		for (int i = 0; i < T; i++) {
			int n = sc.nextInt();
			System.out.println(method[n]);
		}
	}
}