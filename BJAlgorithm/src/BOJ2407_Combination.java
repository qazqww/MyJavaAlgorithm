// https://www.acmicpc.net/problem/2407

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ2407_Combination {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		BigInteger answer = BigInteger.ONE;
		for (int i = n, k = 0; k < m; i--, k++)
			answer = answer.multiply(BigInteger.valueOf(i));
		for (int i = 1; i <= m; i++)
			answer = answer.divide(BigInteger.valueOf(i));
		
		System.out.println(answer);
	}
}