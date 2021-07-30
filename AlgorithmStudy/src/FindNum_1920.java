// https://www.acmicpc.net/problem/1920

/*  0. 이중 for문으로는 시간 초과
 *  1. HashSet, BufferReader 등을 통해 처리 시간 단축
 *  2. 검색을 통해 이분 탐색 내장 함수를 찾아서 시간 단축
 *  
 *  +. 나중에 직접 정렬 혹은 이분 탐색 구현해보기
 */

import java.util.Arrays;
import java.util.Scanner;

public class FindNum_1920 {

	public static void main(String[] args) {
		
		// 이분탐색 내장 라이브러리 사용
		int N, M;
		int[] arr;		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int temp = sc.nextInt();
			if (Arrays.binarySearch(arr, temp) >= 0) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
		}		
		
		
		/* HashSet을 이용한 시간 단축 코드 : Set의 contains 탐색은 시간이 적게 든다.
		int N, M;
		Set<Integer> set = new HashSet<>();
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			set.add(sc.nextInt());
		}
		
		M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			if (set.contains(sc.nextInt())) {
				System.out.println("1");
			}
			else {
				System.out.println("0");
			}
		}
		*/
		
		
		/* 시간 초과 코드
		int N, M;
		int[] arr;		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int temp = sc.nextInt();
			for (int j = 0; j < N; j++) {
				
				if (arr[j] == temp) {
					System.out.println("1");
					break;
				}
				
				if (j == N-1) {
					System.out.println("0");
				}
			}
		}
		*/
	}
}
