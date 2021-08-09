// https://www.acmicpc.net/problem/15650

import java.util.Scanner;

public class NandM2_15650 {
	
	static int N, M;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// 숫자 범위
		M = sc.nextInt();	// 길이
		arr = new int[M];
		combi(0, 1);
	}
	
	static void combi(int index, int n) {
		if (index == M) {
			for (int i = 0; i < M; i++) {
				System.out.printf("%d ", arr[i]);
			}
			System.out.println();
			return;
		}
		
		for (int i = n; i <= N; i++) {
			arr[index] = i;
			combi(index+1, i+1);
		}
	}
}
