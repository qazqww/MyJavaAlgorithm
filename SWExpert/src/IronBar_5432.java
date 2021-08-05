// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWVl47b6DGMDFAXm

import java.util.Scanner;
import java.util.Stack;

public class IronBar_5432 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		for (int t = 0; t < T; t++) {
			Stack<Character> stack = new Stack<>();			// 막대와 레이저 상태를 담을 스택
			char[] arr = sc.nextLine().toCharArray();		// 입력받을 배열
			int sum = 0;
			
			for (int i = 0; i < arr.length; i++) {
				switch(arr[i]) {
				
				case '(':
					if (i+1 < arr.length && arr[i+1] != ')') {	// 직후가 )이 아닐 경우 새로운 막대 추가
						sum += 1;
					}
					stack.add('(');
					break;
					
				case ')':
					if (arr[i-1] == '(') {				// 직전이 (일 경우 () 레이저 완성
						sum += stack.size() - 1;		// 방금 추가된 레이저 '(' 를 제외한 막대 '(' 개수를 더함
					}
					stack.pop();
					break;
				}
			}
			
			System.out.printf("#%d %d\n", t+1, sum);
		}
	}
}