// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=488&sca=99&sfl=wr_hit&stx=1205

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Joker_1205 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean[] nums = new boolean[1000001];
		Queue<Integer> queue = new LinkedList<>();
		int joker = 0;
		int maxNum = -1;
		
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if (temp == 0)
				joker++;	// 조커 개수 체크
			else {
				nums[temp] = true;	// 숫자 유무 체크
				maxNum = Math.max(maxNum, temp);	// 1_000_000까지 가지 않기 위해 최대 숫자를 받아둠
			}
		}
		
		int cnt = 0;		// 조커 포함 연속된 숫자
		int continuous = 0;	// 조커 제외 연속된 숫자
		int missCnt = 0;	// 조커를 사용한 횟수
		int longest = 0;	// 최대 길이
		
		for (int i = 1; i <= maxNum; i++) {
			cnt++;
			continuous++;
			
			if (!nums[i]) {					// 없는 숫자일 경우
				missCnt++;					// 조커 사용
				queue.offer(continuous);	// 후에 조커가 모자를 경우 빼야 할 숫자를 넣어둠
				continuous = 0;
				if (missCnt > joker) {		// 조커를 다 썼을 경우
					cnt -= queue.poll();	// 조커로 이어진 맨 앞 연속된 수를 빼고
					missCnt--;				// 조커를 다시 하나 가져옴
				}
			}
			longest = Math.max(longest, cnt);	// 현재 연속된 숫자가 더 길다면 교체
		}
		
		longest = Math.max(longest, cnt + joker - missCnt);	// 조커가 남았을 경우
		
		System.out.println(longest);
	}
}