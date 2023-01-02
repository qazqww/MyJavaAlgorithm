// https://www.acmicpc.net/problem/10844

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844_EasyStairNum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[][] cnts = new int[10][2];
		for (int i = 1; i < cnts.length; i++) {
			cnts[i][0] = 1;
		}
		
		for (int i = 0; i < N - 1; i++) {
			cnts[0][1] = cnts[1][0];
			cnts[9][1] = cnts[8][0];
			for (int j = 1; j < 9; j++) {
				cnts[j][1] = (cnts[j-1][0] + cnts[j+1][0]) % 1_000_000_000;
			}
			
			for (int j = 0; j < 10; j++) {
				cnts[j][0] = cnts[j][1];
			}
		}
		
		int answer = 0;
		for (int i = 0; i < 10; i++) {
			answer = (answer + cnts[i][0]) % 1_000_000_000;
		}
		System.out.println(answer);
		
	}
}