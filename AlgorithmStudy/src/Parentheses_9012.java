// https://www.acmicpc.net/problem/9012

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Parentheses_9012 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			char[] ps = in.readLine().toCharArray();	// 괄호 문자열을 입력받아 char 배열로 전환
			Stack<Boolean> psStack = new Stack<>();		// 괄호 여닫음을 관리할 스택
			boolean answer = true;
			
			for (char ch : ps) {
				if (ch == '(') {		// 괄호가 열리면 push (값은 상관없다)
					psStack.push(true);
				}
				else if (ch == ')') {	// 괄호가 닫히면 pop
					
					if (psStack.isEmpty()) {	// 스택이 비어있으면 괄호가 열리지 않았는데 닫은 것이므로
						answer = false;			// not valid 처리 후 종료
						break;
					}
					psStack.pop();
				}
			}
			
			if (!psStack.isEmpty())
				answer = false;
			
			System.out.println((answer) ? "YES" : "NO");
		}
	}
}