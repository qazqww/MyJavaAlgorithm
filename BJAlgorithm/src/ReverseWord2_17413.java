// https://www.acmicpc.net/problem/17413

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseWord2_17413 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str = in.readLine();
		StringBuilder sb = new StringBuilder();		// 결과물
		StringBuilder temp = new StringBuilder();	// 뒤집을 단어
		boolean isTag = false;
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if (ch == '<')
				isTag = true;
			else if (ch == '>')
				isTag = false;
			
			if (ch == '<' || ch == ' ') {
				sb.append(temp.reverse());
				temp.setLength(0);
			}
			
			if (isTag || ch == '>' || ch == ' ') {
				sb.append(ch);
			}
			else {
				temp.append(ch);
			}
		}
		
		sb.append(temp.reverse());
		System.out.println(sb);
	}
}