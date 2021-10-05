// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1838&sca=99&sfl=wr_hit&stx=2577

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO2577_ConveyorBeltSushi {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] sushi = new int[N];	// 접시에 담긴 초밥의 종류
		for (int i = 0; i < N; i++)
			sushi[i] = Integer.parseInt(in.readLine());
		
		int now = 1;	// 현재 먹을 수 있는 초밥 종류의 수
		int max = 0;	// 최대로 먹을 수 있는 초밥 종류의 수
		int[] count = new int[d+1];	// 현재 접시들 중 i번째 초밥의 개수
		count[c] = 10_000_000;	// 쿠폰으로 먹는 초밥은 무조건 양수가 되도록 큰 수를 넣음
		
		// 첫 접시들을 선정
		for (int i = 0; i < k; i++) {
			if (count[sushi[i]] == 0)
				now++;
			count[sushi[i]]++;
		}
		max = now;
		
		// 경우의 수를 순서대로 일일이 탐색
		for (int i = k; i < N; i++) {
			if (count[sushi[i-k]] == 1)	// i-k번째 초밥을 뺌. 1개 남았었을 경우 현재 초밥 종류에서 -1
				now--;
			count[sushi[i-k]]--;
			
			if (count[sushi[i]] == 0)	// i번째 초밥을 더함. 없었을 경우 현재 초밥 종류에 +1
				now++;
			count[sushi[i]]++;
			
			max = Math.max(max, now);	// 최대값 갱신
		}
		
		for (int i = N; i < N + k - 1; i++) {
			if (count[sushi[i-k]] == 1)
				now--;
			count[sushi[i-k]]--;
			
			if (count[sushi[i-N]] == 0)
				now++;
			count[sushi[i-N]]++;
			
			max = Math.max(max, now);
		}
		
		System.out.println(max);
	}
}