// https://www.acmicpc.net/problem/7682

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ7682_TicTacToe {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String pan = in.readLine();
			
			if (pan.equals("end"))
				break;
			
			boolean isValid = false;
			int xNum = 0;
			int oNum = 0;
			int empty = 0;
			
			for (int i = 0; i < pan.length(); i++) {
				switch(pan.charAt(i)) {
				case 'X':
					xNum++;
					break;
				case 'O':
					oNum++;
					break;
				case '.':
					empty++;
					break;
				}
			}
			
			if (empty == 0) {
				if (xNum == oNum + 1 && !check(pan, 'O'))
					isValid = true;
			}
			else {
				boolean xWin = check(pan, 'X');
				boolean oWin = check(pan, 'O');
				
				if (xWin && !oWin) {
					if (xNum == oNum + 1)
						isValid = true;
				}
				else if (!xWin && oWin) {
					if (xNum == oNum)
						isValid = true;
				}
			}
			
			System.out.println(isValid ? "valid" : "invalid");
		}
	}
	
	static boolean check(String pan, char ch) {
		if (pan.charAt(0) == ch && (pan.charAt(0) == pan.charAt(1) && pan.charAt(0) == pan.charAt(2)
				|| pan.charAt(0) == pan.charAt(3) && pan.charAt(0) == pan.charAt(6)
				|| pan.charAt(0) == pan.charAt(4) && pan.charAt(0) == pan.charAt(8))) {
			return true;
		}
		else if (pan.charAt(4) == ch && (pan.charAt(4) == pan.charAt(2) && pan.charAt(4) == pan.charAt(6)
				|| pan.charAt(4) == pan.charAt(3) && pan.charAt(4) == pan.charAt(5)
				|| pan.charAt(4) == pan.charAt(1) && pan.charAt(4) == pan.charAt(7))) {
			return true;
		}
		else if (pan.charAt(8) == ch && (pan.charAt(8) == pan.charAt(6) && pan.charAt(8) == pan.charAt(7)
				|| pan.charAt(8) == pan.charAt(2) && pan.charAt(8) == pan.charAt(5))) {
			return true;
		}
		return false;
	}
}
