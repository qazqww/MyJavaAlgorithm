// https://www.acmicpc.net/problem/1707

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1707_BipartiteGraph {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			
			st = new StringTokenizer(in.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			ArrayList<Integer>[] adjList = new ArrayList[V];	// 인접 정점 리스트
			boolean[] visited = new boolean[V];		// 방문 체크 리스트
			int[] checked = new int[V];				// 이분 그래프인지 체크하기 위한 표시 (1과 2로 구분)

			// 그래프 정보를 입력빋는 부분
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;

				if (adjList[start] == null)
					adjList[start] = new ArrayList<>();
				if (adjList[end] == null)
					adjList[end] = new ArrayList<>();

				adjList[start].add(end);
				adjList[end].add(start);
			}

			boolean isBipartite = true;		// 이분그래프인지의 여부
			Queue<Integer> queue;
			
			loop: for (int v = 0; v < V; v++) {	// 탐색하지 않는 정점이 없도록 모든 정점을 순회
				
				if (!visited[v]) {	// 방문하지 않았던 정점이라면 BFS 탐색을 시작
					queue = new LinkedList<>();
					visited[v] = true;
					queue.offer(v);

					int check = 1;		// 체크해둘 수를 만듦
					checked[v] = check;	// 시작 정점은 1로 체크
					
					while (!queue.isEmpty()) {
						check = 3 - check;	// 다음 단계의 정점들은 다르게 체크하기 위해 정점을 교체 (1 <-> 2) 
						int size = queue.size();			// 레벨 관리를 위해 사이즈를 받아두고
						for (int i = 0; i < size; i++) {	// 사이즈만큼만 순회
							int curV = queue.poll();

							if (adjList[curV] == null)	// 이어진 인접 정점이 없다면 종료
								break;

							for (int adjV : adjList[curV]) {	// 인접 정점 순회
								if (!visited[adjV]) {		// 방문하지 않은 정점이라면
									visited[adjV] = true;	// 방문체크
									checked[adjV] = check;	// 이분 그래프를 판단하기 위한 숫자를 저장해둠
									queue.offer(adjV);
								}
								else if (checked[adjV] == checked[curV]) {	// 이미 방문한 정점인데, 체크해둔 수가 같아져버린다면
									isBipartite = false;	// 이분 그래프를 만들 수 없게 되므로
									break loop;				// 반복문 종료
								}
							}
						}
					}
				}
			}
			
			System.out.println(isBipartite ? "YES" : "NO");
		}
	}
}