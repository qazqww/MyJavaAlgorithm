// https://www.acmicpc.net/problem/14696

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SlapMatch_14696 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			int[] card1 = new int[5];	// index 0은 제외
			int[] card2 = new int[5];
			
			// 어린이 A의 딱지 입력
			StringTokenizer st = new StringTokenizer(in.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int i = 0; i < cnt; i++) {
				card1[Integer.parseInt(st.nextToken())]++;
			}
			
			// 어린이 B의 딱지 입력
			st = new StringTokenizer(in.readLine());
			cnt = Integer.parseInt(st.nextToken());
			for (int i = 0; i < cnt; i++) {
				card2[Integer.parseInt(st.nextToken())]++;
			}
			
			char winner = 'D';
			for (int i = 4; i > 0; i--) {
				if (card1[i] > card2[i]) {
					winner = 'A';
					break;
				}
				else if (card1[i] < card2[i]) {
					winner = 'B';
					break;
				}
			}
			
			System.out.println(winner);
		}
	}
}