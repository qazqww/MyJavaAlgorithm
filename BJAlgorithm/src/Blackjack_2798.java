// https://www.acmicpc.net/problem/2798

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Blackjack_2798 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		
		int[] cards = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++)
			cards[i] = Integer.parseInt(st.nextToken());
		
		int sum = 0;					// 카드 3장의 합을 담아둘 임시 변수
		int gap = Integer.MAX_VALUE;	// 타겟 넘버와 가장 적은 차이를 담아둠
		int answer = 0;					// gap이 가장 적을 때의 합
		
		for (int i = 0; i < N - 2; i++) {
			sum += cards[i];		// 카드를 골라 총합에 더함 
			if (sum > target) {		// 타겟보다 수가 높아질 경우
				sum -= cards[i];	// 고른 카드를 다시 빼고
				continue;			// 다음 카드로 넘어감
			}
			for (int j = i + 1; j < N - 1; j++) {
				sum += cards[j];
				if (sum > target) {
					sum -= cards[j];
					continue;
				}
				for (int k = j + 1; k < N; k++) {
					sum += cards[k];
					if (sum > target) {
						sum -= cards[k];
						continue;
					}
					if (target - sum < gap) {
						gap = target - sum;
						answer = sum;
					}
					sum -= cards[k];
				}
				sum -= cards[j];
			}
			sum -= cards[i];
		}
		
		System.out.println(answer);
	}
}