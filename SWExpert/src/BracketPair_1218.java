// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD

import java.util.Scanner;
import java.util.Stack;

public class BracketPair_1218 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// Stack 라이브러리 사용 코드
		for (int t = 0; t < 10; t++) {
			boolean isValid = true;
			Stack<Character> stack1 = new Stack<>();
			Stack<Character> stack2 = new Stack<>();
			Stack<Character> stack3 = new Stack<>();
			Stack<Character> stack4 = new Stack<>();
			
			int len = sc.nextInt();
			sc.nextLine();
			char[] arr = sc.nextLine().toCharArray();
			
			for (int i = 0; i < len; i++) {
				if (!isValid)
					break;
				
				switch(arr[i]) {
				case '(':
					stack1.push('a');
					break;
				case '{':
					stack2.push('a');
					break;
				case '[':
					stack3.push('a');
					break;
				case '<':
					stack4.push('a');
					break;
				case ')':
					if (stack1.isEmpty()) {
						isValid = false;
						break;
					}
					stack1.pop();
					break;
				case '}':
					if (stack2.isEmpty()) {
						isValid = false;
						break;
					}
					stack2.pop();
					break;
				case ']':
					if (stack3.isEmpty()) {
						isValid = false;
						break;
					}
					stack3.pop();
					break;
				case '>':
					if (stack4.isEmpty()) {
						isValid = false;
						break;
					}
					stack4.pop();
					break;
				}
			}
			
			if (!stack1.isEmpty() || !stack2.isEmpty() || !stack3.isEmpty() || !stack4.isEmpty()) {
				isValid = false;
			}
			
			System.out.printf("#%d %d\n", t+1, (isValid) ? 1 : 0);
		}
	}
}
		
		/* 배열 사용 코드
		Scanner sc = new Scanner(System.in);
		
		for (int t = 0; t < 10; t++) {
			int[] bracket = new int[4];		// 열려있는 괄호 개수를 체크 => 0:(), 1:{}, 2:[], 3:<>
			boolean isValid = true;
			
			int len = sc.nextInt();
			sc.nextLine();
			char[] arr = sc.nextLine().toCharArray();
			
			for (int i = 0; i < len; i++) {
				if (!isValid)	// 도중에 유효하지 않음이 판단되면 읽어들이기 종료
					break;
				
				switch(arr[i]) {
				case '(':
					bracket[0]++;
					break;
				case ')':
					if (bracket[0] == 0) {	// 열린 괄호가 없는데 닫았을 경우 : 유효X
						isValid = false;
						break;
					}
					bracket[0]--;
				break;
				
				case '{':
					bracket[1]++;
					break;
				case '}':
					if (bracket[1] == 0) {
						isValid = false;
						break;
					}
					bracket[1]--;
					break;
					
				case '[':
					bracket[2]++;
					break;
				case ']':
					if (bracket[2] == 0) {
						isValid = false;
						break;
					}
					bracket[2]--;
					break;
					
				case '<':
					bracket[3]++;
					break;
				case '>':
					if (bracket[3] == 0) {
						isValid = false;
						break;
					}
					bracket[3]--;
					break;
				}
			}
			
			for (int i = 0; i < bracket.length; i++) {
				if (bracket[i] > 0) {	// 입력이 끝났는데도 열려있는 괄호가 남아있을 경우 : 유효X
					isValid = false;
				}
			}
			
			System.out.printf("#%d %d\n", t+1, (isValid) ? 1 : 0);
		}
		*/