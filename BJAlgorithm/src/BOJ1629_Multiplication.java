// https://www.acmicpc.net/problem/1629

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629_Multiplication {
	static long A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());

		System.out.println(divide(B) % C);
	}

	static long divide(long num) {

		if (num == 0)
			return 0;
		
		if (num == 1)
			return A;

		if (num % 2 == 0) {
			long next = divide(num / 2) % C;
			next = next * next % C;
			return next;
		}
		else {
			long next = divide(num / 2) % C;
			next = ((next * next) % C) * A % C;
			return next;
		}
	}
}