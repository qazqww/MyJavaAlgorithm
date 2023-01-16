// https://www.acmicpc.net/problem/2448

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2448_Star11 {
	static StringBuilder[] sbs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		sbs = new StringBuilder[N];
		for (int i = 0; i < N; i++) {
			sbs[i] = new StringBuilder();
		}
		
		star(N);
		
		for (int i = 0; i < N; i++) {
			StringBuilder empty = new StringBuilder();
			for (int j = 0; j < N - i - 1; j++) {
				empty.append(" ");
			}
			System.out.print(empty);
			System.out.print(sbs[i]);
			System.out.println(empty);
		}
	}
	
	static StringBuilder[] star(int line) {
		if (line == 3) {
			sbs[0].append("*");
			sbs[1].append("* *");
			sbs[2].append("*****");
			return Arrays.copyOfRange(sbs, 0, 3);
		}
		
		StringBuilder[] basic = star(line / 2);
		
		int emptyCnt = line - 1;
		for (int i = line / 2; i < line; i++) {
			sbs[i].append(basic[i - line / 2]);
			for (int j = 0; j < emptyCnt; j++) {
				sbs[i].append(" ");
			}
			emptyCnt -= 2;
			sbs[i].append(basic[i - line / 2]);
		}
		return Arrays.copyOfRange(sbs, 0, line);
	}
}
/*
3 라인 -> 빈칸 5칸
6 라인 -> 빈칸 11칸
12 라인 -> 빈칸 23칸

3번째 줄까지 완성
-> 4~6번째 줄까지 복사
-> 빈칸 적용
-> 다시 복사 하면 6번째 줄까지 완성

-> 7~12번째 줄까지 복사
-> 빈칸 적용
-> 다시 복사하면 12번째 줄까지 완성

StringBuilder[] 로 반환하여
length번째 부터 적용
 */