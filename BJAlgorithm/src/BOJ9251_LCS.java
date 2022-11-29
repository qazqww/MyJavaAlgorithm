// https://www.acmicpc.net/problem/9251

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9251_LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String first = in.readLine();
		String second = in.readLine();
		
		int[][] map = new int[first.length() + 1][second.length() + 1];
		for (int i = 1; i < first.length() + 1; i++) {
			for (int j = 1; j < second.length() + 1; j++) {
				if (first.charAt(i - 1) == second.charAt(j - 1)) {
					map[i][j] = map[i-1][j-1] + 1;
				}
				else {
					map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
				}
			}
		}
		
		System.out.println(map[first.length()][second.length()]);
	}
}