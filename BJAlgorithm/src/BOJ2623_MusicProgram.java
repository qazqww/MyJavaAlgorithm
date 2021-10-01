// https://www.acmicpc.net/problem/2623

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2623_MusicProgram {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		// 답을 임시적으로 담을 문자열
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] list = new ArrayList[N];	// 자식 정점을 담을 리스트
		for (int i = 0; i < N; i++)
			list[i] = new ArrayList<>();
		int[] inDegree = new int[N];	// 진입 차수를 담을 배열

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken());

			int[] order = new int[num];		// 보조 PD의  순서를 임시적으로 담음
			order[0] = Integer.parseInt(st.nextToken()) - 1;
			for (int j = 1; j < num; j++) {
				order[j] = Integer.parseInt(st.nextToken()) - 1;
				list[order[j - 1]].add(order[j]);
				inDegree[order[j]]++;
			}
		}

		while (true) {
			boolean isEnd = true;	// 진입 차수가 0인 정점이 없다면 종료하도록 플래그를 둠
			Queue<Integer> queue = new LinkedList<>();	// 진입 차수가 0인 정점의 자식 정점들을 담을 큐
			for (int i = 0; i < N; i++) {
				if (inDegree[i] == 0) {		// 진입 차수가 0이라면
					sb.append(i+1 + "\n");	// 답에 추가해놓고
					isEnd = false;			// 종료를 보류
					inDegree[i]--;			// 현재 진입차수는 -1로 두어 다음에 탐색하지 않도록 설정
					
					for (int num : list[i])	// 자식 정점들을 큐에 저장
						queue.offer(num);
				}
			}

			for (int num : queue)	// 자식 정점들의 진입 차수를 하나씩 감소
				inDegree[num]--;
			
			if (isEnd)
				break;
		}

		// 모든 정점의 진입차수가 -1이라면 성공적으로 완료
		int sum = 0;
		for (int i = 0; i < N; i++)
			sum += inDegree[i];
		
		System.out.println((sum == -N) ? sb : 0);
	}
}