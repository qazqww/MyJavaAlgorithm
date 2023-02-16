// https://www.acmicpc.net/problem/12891

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12891_DNAPassword {
	static int[] need = new int[4];
	static int[] have = new int[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String password = in.readLine();
		password = password.replace('A', '0');
		password = password.replace('C', '1');
		password = password.replace('G', '2');
		password = password.replace('T', '3');
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < 4; i++) {
			need[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		for (int i = 0; i < P; i++) {
			have[password.charAt(i) - '0']++;
		}
		if (isSafe()) {
			answer++;
		}
			
		for (int i = P; i < S; i++) {
			have[password.charAt(i - P) - '0']--;
			have[password.charAt(i) - '0']++;
			if (isSafe()) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	
	static boolean isSafe() {
		for (int i = 0; i < 4; i++) {
			if (have[i] < need[i])
				return false;
		}
		return true;
	}
}