package B5toS4;// https://www.acmicpc.net/problem/1010

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1010_SetBridge {
	
	static int[][] record = new int[30][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 30; i++) {
			record[i] = new int[i+1];
			record[i][0] = 1;
			record[i][i] = 1;
		}

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			System.out.println(combi(M, N));
		}
	}
	
	static int combi(int n, int r) {
		if (record[n][r] != 0)
			return record[n][r];
		else {
			int result = combi(n-1, r) + combi(n-1, r-1);
			record[n][r] = result;
			return result;
		}
	}
	
}