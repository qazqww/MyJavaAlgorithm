// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV13zo1KAAACFAYh

import java.util.Arrays;
import java.util.Scanner;

public class Mode_1204 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[101];
		int T = sc.nextInt();
		for (int i = 0; i < 10; i++) {
			sc.nextInt();
			for (int j = 0; j < 1000; j++) {
				arr[sc.nextInt()]++;
			}
			
			int maxNum = -1;
			int maxCount = 0;
			for (int j = 0; j < 101; j++) {
				if (arr[j] >= maxCount) {
					maxCount = arr[j];
					maxNum = j;
				}
			}
			
			System.out.printf("#%d %d\n", i + 1, maxNum);
			Arrays.fill(arr, 0);
		}

	}
}
