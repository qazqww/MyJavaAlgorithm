// https://www.acmicpc.net/problem/1592

import java.util.Scanner;

public class Youngsik_1592 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int limit = sc.nextInt();
		int L = sc.nextInt();
		
		int[] friends = new int[N];		// 0 ~ N-1 자리의 친구들 : 공 받은 횟수
		int next = 0;					// 첫 사람이 공 받을 준비
		
		while (true) {
			friends[next]++;				// 공을 받음
			if (friends[next] == limit)		// 공 받은 횟수가 제한 횟수를 넘기면 종료
				break;
			
			next = (friends[next] % 2 == 0) ?	// 다음 받을 사람을 확인
					(next - L) % N :			// 짝수이면 반시계 (-)
					(next + L) % N;				// 홀수이면 시계 (+)
			
			if (next < 0)
				next += N;
		}
		
		int sum = -1;	// 받기만 하고 던지지 않은 1번을 제외
		for (int cnt : friends) {	// 친구들이 공 받은 횟수를 모두 더함
			sum += cnt;
		}

		System.out.println(sum);
	}
}