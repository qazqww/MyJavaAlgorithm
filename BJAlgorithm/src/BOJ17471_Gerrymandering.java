// https://www.acmicpc.net/problem/17471

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471_Gerrymandering {
	
	static int N;
	static int min = Integer.MAX_VALUE;
	static boolean[] isRed;
	static int[] people;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		isRed = new boolean[N];	// 선거구를 빨강(true), 파랑(false)으로 나눔
		people = new int[N];	// 구역 별 인구 수
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++)
			people[i] = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 100_000);
			map[i][i] = 0;
		}
		
		for (int from = 0; from < N; from++) {
			st = new StringTokenizer(in.readLine());
			
			int cnt = Integer.parseInt(st.nextToken());
			for (int i = 0; i < cnt; i++) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				map[from][to] = 1;
				map[to][from] = 1;
			}
		}
		
		subset(0);
		
		System.out.println((min == Integer.MAX_VALUE) ? -1 : min);
	}
	
	// 서로 다른 두 점이 연결되었는지 BFS로 확인하는 메서드
	static boolean isConnected(int start, int end, boolean r) {
		boolean[] visited = new boolean[N];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			if (cur == end)		// 도착점에 도달했을 시 true 리턴
				return true;
			
			for (int i = 0; i < N; i++) {
				// 연결 되어있고, 방문하지 않은 구역이고, 구역 색이 같은 곳이라면 탐색
				if (map[cur][i] < 100_000 && !visited[i] && isRed[i] == r) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
		
		return false;	// 도착점에 도달하지 못한 채로 탐색이 끝났으므로 false 리턴
	}
	
	// 부분집합 생성 메서드
	static void subset(int index) {
		
		if (index == N) {	// 부분집합이 완성되면
			
			int firstRed = -1;		// 빨강 구역 중 하나의 위치 
			int firstBlue = -1;		// 파랑 구역 중 하나의 위치
			int redCnt = 0;		// 빨강 구역의 총 인구 수
			int blueCnt = 0;	// 파랑 구역의 총 인구 수
			
			for (int i = 0; i < N; i++) {
				if (isRed[i]) {
					// 첫 빨강 구역일 경우 바로 포함
					// 아닐 경우 첫 빨강 구역과 새로운 구역이 연결되었는지 확인
					if (firstRed == -1 || isConnected(firstRed, i, isRed[i])) {
						firstRed = i;
						redCnt += people[i];
					}
					else	// 연결되지 않았다면 불가능한 방법에 해당
						return;
				}
				else {
					if (firstBlue == -1 || isConnected(firstBlue, i, isRed[i])) {
						firstBlue = i;
						blueCnt += people[i];
					}
					else
						return;
				}
			}
			
			if (blueCnt == 0 || redCnt == 0)	// 모두 빨강 구역이거나 모두 파랑 구역인 경우 불가능한 방법에 해당
				return;
			
			// 차이가 최소값이라면 갱신
			int gap = Math.abs(redCnt - blueCnt);
			min = Math.min(min, gap);
			
			return;
		}

		isRed[index] = false;
		subset(index+1);
		isRed[index] = true;
		subset(index+1);
	}
}