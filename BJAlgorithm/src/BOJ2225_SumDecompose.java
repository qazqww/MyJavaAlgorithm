// https://www.acmicpc.net/problem/2225

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2225_SumDecompose {
	static final int DIV = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] table = new int[K][N + 1];
		for (int r = 0; r < K; r++) {
			table[r][0] = 1;
		}
		for (int c = 0; c < N + 1; c++) {
			table[0][c] = 1;
		}
		
		for (int r = 1; r < K; r++) {
			for (int c = 1; c < N + 1; c++) {
				table[r][c] = ((table[r][c-1] % DIV) + (table[r-1][c] % DIV)) % DIV;
			}
		}
		
		System.out.println(table[K - 1][N]);
	}
}

/*
k개 사용 => k 0 0 0 0 ....

6을 만드는 숫자 4개

0을 만드는 숫자 3개
....
5를 만드는 숫자 3개 => 0을 만드는 숫자 2개 ~ 5를 만드는 숫자 2개

N을 만드는 숫자 K개 = 0~N을 만드는 숫자 (K-1)개


K/N	0	1	2	3	4	5	6
1	1	1	1	1	1	1	1
2	1	2	3	4	5	6	7
3	1	3	6	10	15	21	28
4	1
5	1
6	1
*/