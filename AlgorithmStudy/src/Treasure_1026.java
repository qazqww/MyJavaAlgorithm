// https://www.acmicpc.net/problem/1026

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Treasure_1026 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int answer = 0;
		
		int N = sc.nextInt();
		Integer[] arrA = new Integer[N];
		Integer[] arrB = new Integer[N];
		boolean[] usedB = new boolean[N];
		
		for (int i = 0; i < N; i++)
			arrA[i] = sc.nextInt();
		for (int i = 0; i < N; i++)
			arrB[i] = sc.nextInt();

		Arrays.sort(arrA);
		
		// B를 복사하여 역순으로 정렬
		Integer[] copyB = Arrays.copyOf(arrB, arrB.length);
		Arrays.sort(copyB, Collections.reverseOrder());
		
		for (int i = 0; i < N; i++) {
			// answer += arrA[i] * copyB[i];
			
			// 복사한 B의 순서(가장 큰 수부터)에 맞는 수를 원래 B에서 찾아 A를 가장 작은 수부터 곱함
			// 사용한 B는 다음에는 사용하지 않도록 체크한다
			// 재배열한 A배열이 필요하다면, arrA2[j] = arrA[i] 식으로 넣으면 될 것 같다
			int biggest = copyB[i];
			for (int j = 0; j < N; j++) {
				if (!usedB[j] && arrB[j] == biggest) {
					answer += arrA[i] * arrB[j];
					usedB[j] = true;
					break;
				}
			}
		}
		
		System.out.println(answer);
	}
}