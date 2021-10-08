// https://www.acmicpc.net/problem/5014

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ5014_Startlink {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int h = sc.nextInt();
		int now = sc.nextInt();
		int goal = sc.nextInt();
		int up = sc.nextInt();
		int down = sc.nextInt();
		
		int[] step = new int[h + 1]; // 층까지 눌러야하는 버튼 수
		boolean[] visited = new boolean[h + 1]; // 방문 여부
		
		Arrays.fill(step, 10_000_000); // 최소값을 구하기 위해 큰 값으로 채워둠
		
		// 시작점 처리
		visited[now] = true;
		step[now] = 0;
		
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.offer(new Info(0, now));
		
		boolean isEnd = false;
		while (!pq.isEmpty()) { // 목표 층에 도달할 때까지 or 더이상 갈 곳이 없을 때까지 탐색
			Info info = pq.poll();
			
			if (info.floor == goal) { // 목표 층에 도착했을 경우 종료
				isEnd = true;
				break;
			}
			
			int upTarget = info.floor + up;		// 위 버튼으로 갈 수 있는 층
			int downTarget = info.floor - down;	// 아래 버튼으로 갈 수 있는 층
			
			if (upTarget <= h && !visited[upTarget]) { // 건물을 벗어나지 않았고, 방문한 적 없는 층이면
				step[upTarget] = info.cnt + 1;				// 현재까지 누른 버튼 +1을 새로 도달한 층에 할당
				pq.offer(new Info(info.cnt + 1, upTarget));	// 다음 탐색을 위해 pq에 삽입
				visited[upTarget] = true;					// 방문 체크
			}
			
			if (downTarget >= 1 && !visited[downTarget]) {
				step[downTarget] = info.cnt + 1;
				pq.offer(new Info(info.cnt + 1, downTarget));
				visited[downTarget] = true;
			}
		}
		
		System.out.println(isEnd ? step[goal] : "use the stairs");
	}
	
	static class Info implements Comparable<Info> {
		int cnt;
		int floor;
		public Info(int cnt, int floor) {
			this.cnt = cnt;
			this.floor = floor;
		}
		
		@Override
		public int compareTo(Info o) {
			return Integer.compare(cnt, o.cnt);
		}
	}
}