// https://www.acmicpc.net/problem/13116

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13116_No30 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			while (true) {
				if (A > B) {
					A /= 2;
				}
				else if (B > A) {
					B /= 2;
				}
				else {
					sb.append(A * 10 + "\n");
					break;
				}
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}