// https://www.acmicpc.net/problem/9935

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935_StringBomb {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		String bomb = in.readLine();
		
		char first = bomb.charAt(0);
		int nextIdx = 0;
		
		StringBuilder answer = new StringBuilder();
		Stack<Integer> intStack = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			answer.append(str.charAt(i));
			if (str.charAt(i) == first || str.charAt(i) == bomb.charAt(nextIdx)) {
				if (str.charAt(i) == first) {
					intStack.push(nextIdx);
					nextIdx = 0;
				}
				nextIdx++;
			}
			else {
				intStack.clear();
				nextIdx = 0;
			}
			
			if (nextIdx == bomb.length()) {
				answer.setLength(answer.length() - bomb.length());
				nextIdx = intStack.isEmpty() ? 0 : intStack.pop();
			}
		}
		
		System.out.println(answer.length() == 0 ? "FRULA" : answer);
	}
}