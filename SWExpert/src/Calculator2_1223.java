// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14nnAaAFACFAYD

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Calculator2_1223 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 0; t < 10; t++) {
			int len = Integer.parseInt(in.readLine());
			char[] exp = in.readLine().toCharArray();	// 중위 표기식을 입력받음
			char[] result = new char[len];				// 후위 표기식을 저장할 배열
			boolean firstPlus = true;					// 첫번째 더하기인지 체크

			int index = 0;
			for (int i = 0; i < len; i++) {
				char ch = exp[i];

				if (ch == '+') {				// 덧셈 : 다른 덧셈이 오거나 곱셈 이후 처리
					if (!firstPlus) {			// 첫 덧셈이 아닐 경우 처리
						result[index++] = ch;
					} else {
						firstPlus = false;		// 첫 덧셈일 경우 flag 표시 해제
					}
				} else if (ch == '*') {
					result[index++] = exp[++i];	// 곱셉은 다음 수를 불러와서
					result[index++] = ch;		// 한꺼번에 바로 처리
				} else {
					result[index++] = ch;		// 일반 숫자는 그냥 처리
				}
			}
			if (!firstPlus)				// 마지막 덧셈 처리
				result[index] = '+';

			// System.out.println(Arrays.toString(result));
			
			Stack<Integer> stack = new Stack<>();		// 후위 표기식을 계산할 스택
			
			for (int i = 0; i < len; i++) {
				switch (result[i]) {
				case '+': {
					int num1 = stack.pop();
					int num2 = stack.pop();
					stack.push(num1 + num2);
				}
					break;
				case '*': {
					int num1 = stack.pop();
					int num2 = stack.pop();
					stack.push(num1 * num2);
				}
					break;
				default:
					stack.push(result[i] - '0');
					break;
				}
			}

			System.out.printf("#%d %d\n", t+1, stack.pop());
		}
	}
}