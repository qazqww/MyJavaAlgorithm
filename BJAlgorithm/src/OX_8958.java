// https://www.acmicpc.net/problem/8958

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OX_8958 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			int score = 0;
			int stack = 0;		// 연속으로 맞춘 문제 개수
			String str = in.readLine();
			
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == 'O') {
					stack++;
					score += stack;
				}
				else
					stack = 0;
			}
			
			System.out.println(score);
		}
	}
}