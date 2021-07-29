// https://www.acmicpc.net/problem/1920
/*
 * 계속 시간초과가 떠서 구글링을 해보니 Set 자료구조의 탐색 시간이 빠르다는 사실을 알았다.
 * BufferedReader를 이용하여 input 시간을 줄이는 것도 좋을 것 같다.
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FindNum_1920 {

	public static void main(String[] args) {
		
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
		
		/*
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
		
		/*
		int N, M;
		List<Integer> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			list.add(sc.nextInt());
		}
		
		M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int temp = sc.nextInt();
			if (list.contains(temp)) {
				System.out.println("1");
			}
			else {
				System.out.println("0");
			}
		}*/		
	}
}
