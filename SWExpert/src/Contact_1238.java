// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Contact_1238 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 0; t < 10; t++) {
			ArrayList<Integer>[] list = new ArrayList[101];		// 정점의 정보를 담은 ArrayList 배열
			boolean[] visited = new boolean[101];				// 방문 여부 체크
			int maxLevel = -1;									// 가장 마지막에 연락받는 단계 
			int maxNum = -1;									// 마지막 단계 중 가장 높은 수

			StringTokenizer st = new StringTokenizer(in.readLine());
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			// 인접 리스트 만들기
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < len / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				if (list[from] == null)
					list[from] = new ArrayList<Integer>();
				if (list[to] == null)
					list[to] = new ArrayList<Integer>();

				list[from].add(to);
			}

			// BFS로 그래프 순회하기
			Queue<int[]> queue = new LinkedList<>();	// { 값, 레벨(너비) } 를 나타내는 int 배열을 담는 Queue
			queue.offer(new int[] { start, 0 });		// 첫 정점을 넣어준다
			visited[start] = true;						// 첫 정점 방문 표시

			while (!queue.isEmpty()) {
				int[] current = queue.poll();
				int value = current[0];
				int level = current[1];
				
				if (level > maxLevel) {		// 가장 마지막 연락 단계를 구하기 위해, 너비를 체크한다
					maxLevel = level;
					maxNum = -1;			// 다음 단계가 더 있었을 경우, 최대값을 초기화해준다.
				}
				
				if (level == maxLevel && value > maxNum) {		// 현재 레벨에서 가장 높은 수를 담아둔다
					maxNum = value;
				}
				
				for (int i = 0; i < list[value].size(); i++) {		// 다음 레벨의 방문하지 않은 정점들을 담는다
					int next = list[value].get(i);
					if (!visited[next]) {
						visited[next] = true;
						queue.offer(new int[] { next, level + 1 });
					}
				}
			}

			System.out.printf("#%d %d\n", t+1, maxNum);
		}
	}
}