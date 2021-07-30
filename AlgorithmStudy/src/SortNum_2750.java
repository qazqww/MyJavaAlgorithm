// https://www.acmicpc.net/problem/2750

import java.util.Arrays;
import java.util.Scanner;

public class SortNum_2750 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		arr = merge(Arrays.copyOfRange(arr, 0, arr.length/2), Arrays.copyOfRange(arr, arr.length/2, arr.length));
		
		for (int i = 0; i < N; i++) {
			System.out.println(arr[i]);
		}

	}
	
	// 병합 정렬??
	static int[] merge(int[] l, int[] r) {
		if (l.length >= 2) {
			l = merge(Arrays.copyOfRange(l, 0, l.length/2), Arrays.copyOfRange(l, l.length/2, l.length));
		}
		if (r.length >= 2) {
			r = merge(Arrays.copyOfRange(r, 0, r.length/2), Arrays.copyOfRange(r, r.length/2, r.length));
		}
		
		int[] tempArr = new int[l.length + r.length];
		int lCount = 0;
		int rCount = 0;
		int now = 0;
		
		while (lCount < l.length && rCount < r.length) {
			if (l[lCount] > r[rCount]) {
				tempArr[now++] = r[rCount++];
			} else {
				tempArr[now++] = l[lCount++];
			}
		}
		
		while (rCount < r.length) {
			tempArr[now++] = r[rCount++];
		}
		while (lCount < l.length) {
			tempArr[now++] = l[lCount++];
		}
		
		return tempArr;		
	}

}
