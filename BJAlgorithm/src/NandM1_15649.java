// https://www.acmicpc.net/problem/15649

import java.util.Scanner;

public class NandM1_15649 {

	static int N;	// 수의 범위
	static int M;	// 순열의 길이
	static int[] permu;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		permu = new int[M];
		isSelected = new boolean[N];
		
		permutation(0);
	}
	
	static void permutation(int index) {
		if (index == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(permu[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) {
				continue;
			}
			isSelected[i] = true;
			permu[index] = i+1;
			permutation(index+1);
			isSelected[i] = false;
		}
	}
}
