// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh

import java.util.Arrays;
import java.util.Scanner;

public class Flatten_1208 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[100];				// 각 높이값을 가진 위치의 개수를 담는 변수

		for (int k = 0; k < 10; k++) {
			int count = sc.nextInt();
			int max = 99;
			int min = 0;
			Arrays.fill(arr, 0);
			
			for (int i = 0; i < 100; i++) {
				arr[sc.nextInt() - 1]++;
			}

			// 가장 낮은 위치값
			for (int i = 0; i < 100; i++) {
				if (arr[i] > 0) {
					min = i;
					break;
				}
			}
			
			// 가장 높은 위치값
			for (int i = 99; i >= 0; i--) {
				if (arr[i] > 0) {
					max = i;
					break;
				}
			}

			// 덤프 진행
			for (int i = 0; i < count; i++) {	// 상자를 빼려는 곳의 높이가 h일 때
				arr[max]--;						// 상자를 빼면 높이 h인 곳은 하나가 줄고
				arr[max - 1]++;					// h - 1인 곳이 하나 늘어난다.
				arr[min]--;						// 상자를 쌓는 곳도 마찬가지로
				arr[min + 1]++;					// 쌓은 위치의 높이가 1 증가하므로 카운트해준다.

				// max, min 다시 구하기
				if (arr[max] == 0) {
					while (arr[max] == 0) {
						max--;
					}
				}
				if (arr[min] == 0) {
					while (arr[min] == 0) {
						min++;
					}
				}
			}

			System.out.printf("#%d %d\n", k+1, max - min);
		}
	}
}
