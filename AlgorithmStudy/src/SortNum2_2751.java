// https://www.acmicpc.net/problem/2750

/* 참고할만한 게시물
 * https://www.acmicpc.net/board/view/31887
 * 
 * 시간초과를 피하기 위해 시도해본 것
 * - 분할 파트와 합병 파트 분리
 * - System.arraycopy : copyOfRange와 같은 메서드였다
 * - StringBuilder : O(log2n)을 구현해도 계속 시간초과가 나서  사용
 * 
 * 실수했던 것
 * - divide 메서드를 실행만 하고 대입하지 않음
 */

import java.util.Arrays;
import java.util.Scanner;

public class SortNum2_2751 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N  = sc.nextInt();
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		arr = divide(arr);
		
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]+"\n");
		}
		System.out.println(sb);
	}
	
	static int[] divide(int[] arr) {
		if (arr.length == 1) {
			return arr;
		}

		int[] arrL = Arrays.copyOfRange(arr, 0, arr.length/2);
		int[] arrR = Arrays.copyOfRange(arr, arr.length/2, arr.length);
		arrR = divide(arrR);
		arrL = divide(arrL);
		return merge(arrL, arrR);
	}
	
	static int[] merge(int[] l, int[] r) {
		
		int[] tempArr = new int[l.length + r.length];
		int lCount = 0;
		int rCount = 0;
		int now = 0;
		
		while (lCount < l.length && rCount < r.length) {
			if (l[lCount] < r[rCount]) {
				tempArr[now++] = l[lCount++];
			} else {
				tempArr[now++] = r[rCount++];
			}
		}
		
		while (lCount < l.length) {
			tempArr[now++] = l[lCount++];
		}
		while (rCount < r.length) {
			tempArr[now++] = r[rCount++];
		}
		
		return tempArr;
	}
}