// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Millionaire_1859 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int days = Integer.parseInt(in.readLine());
			int[] price = new int[days];	// 각 날짜의 매매가
			int[] cnt = new int[10001];		// 각 매매가가 나온 횟수
			long answer = 0;
			
			// 입력
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < days; i++) {
				price[i] = Integer.parseInt(st.nextToken());
				cnt[price[i]]++;
			}
			
			int day = -1;
			for (int i = 10000; i >= 0; i--) {	// 높은 가격부터 체크
				while (cnt[i] > 0) {
					while (++day < days) {
						if (price[day] == i) {	// 해당 가격이 나올때까지
							cnt[i]--;
							break;
						}
						answer += i - price[day];	// 그 가격으로 판매, 이익을 답에 더함
						cnt[price[day]]--;		// 매매가 횟수 1 감소
					}
				}
				
				if (day == days - 1)
					break;
			}
			
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
}