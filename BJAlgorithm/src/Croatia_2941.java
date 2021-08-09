// https://www.acmicpc.net/problem/2941

import java.util.Scanner;
import java.util.Stack;

public class Croatia_2941 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Character> stack = new Stack<>();
		int answer = 0;		// 총 크로아티아 알파벳 개수
		
		char[] ch = sc.nextLine().toCharArray();
		for (int i = 0; i < ch.length; i++) {
			stack.push(ch[i]);
		}
		
		while (!stack.isEmpty()) {
			char next;	// 다음 문자를 볼 필요가 있을 시 임시로 담아줄 변수
			
			switch (stack.pop()) {
			
			case '-':	// -는 특수문자이고, 무조건 이전 문자가 1(c 또는 d)개 오므로 바로 추가 pop 실행 후 1개로 계산
				stack.pop();
				answer++;
				break;
				
			case '=':	// =도 특수문자이므로, c= s= 는 위와 같음. z= (dz=) 는 이전 문자까지 확인 후, d이면 추가로 pop 실행
				next = stack.pop();
				if (next == 'z' && !stack.isEmpty() && stack.peek() == 'd') {
						stack.pop();
				}
				answer++;
				break;
				
			case 'j':	// j는 문자이므로, 이전 문자를 확인 후 l 또는 n이면 추가로 pop 실행
				if (!stack.isEmpty()) {
					next = stack.peek();
					if (next == 'l' || next == 'n') {
						stack.pop();
					}
				}
				answer++;
				break;
				
			default:	// 나머지 문자는 그냥 1개로 계산
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}