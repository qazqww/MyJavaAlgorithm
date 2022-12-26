// https://www.acmicpc.net/problem/1309

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1309_Zoo {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[][] cnt = new int[3][2];
		cnt[0][0] = 1; // 위가 채워진 경우
		cnt[1][0] = 1; // 아래가 채워진 경우
		cnt[2][0] = 1; // 둘 다 비어있는 경우
		
		for (int i = 1; i < N; i++) {
			cnt[0][1] = (cnt[1][0] + cnt[2][0]) % 9901;
			cnt[1][1] = (cnt[0][0] + cnt[2][0]) % 9901;
			cnt[2][1] = ((cnt[0][0] + cnt[1][0]) % 9901 + cnt[2][0]) % 9901;
			
			cnt[0][0] = cnt[0][1];
			cnt[1][0] = cnt[1][1];
			cnt[2][0] = cnt[2][1];
		}
		
		System.out.println(((cnt[0][0] + cnt[1][0]) % 9901 + cnt[2][0]) % 9901);
	}
}