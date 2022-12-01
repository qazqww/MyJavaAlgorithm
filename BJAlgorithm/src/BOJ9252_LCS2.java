// https://www.acmicpc.net/problem/9252

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9252_LCS2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = in.readLine();
		String str2 = in.readLine();
		
		int[][] map = new int[str1.length() + 1][str2.length() + 1];
		
		for (int i = 1; i < str1.length() + 1; i++) {
			for (int j = 1; j < str2.length() + 1; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					map[i][j] = map[i-1][j-1] + 1;
				}
				else {
					if (map[i-1][j] > map[i][j-1]) {
						map[i][j] = map[i-1][j];
					}
					else {
						map[i][j] = map[i][j-1];
					}
				}
			}
		}
		
		int findY = str1.length();
		int findX = str2.length();
		int len = map[str1.length()][str2.length()];
		StringBuilder sb = new StringBuilder();
		
		while (len > 0) {
			if (map[findY-1][findX] == len)
				findY--;
			else if (map[findY][findX-1] == len)
				findX--;
			else {
				sb.append(str1.charAt(findY - 1));
				findY--;
				findX--;
				len--;
			}
		}
		sb = sb.reverse();
		
		System.out.println(map[str1.length()][str2.length()]);
		if (map[str1.length()][str2.length()] > 0)
			System.out.println(sb);
	}
}


/* 
 * 0	-	Y	O	U	I	Y	O	U
 * -
 * Y		1	1	1	1	1	1	1
 * I		1	1	1	2	2	2	2
 * Y		1	1	1	2	3	3	3
 * O		1	2	2	2	3	4	4
 * U		1	2	3	3	3	4	5
 */