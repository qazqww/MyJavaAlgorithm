// https://www.acmicpc.net/problem/1753

// 시간 초과, 메모리 초과로 실패

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ShortestRoute_1753 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int V = Integer.parseInt(st.nextToken()) + 1;
		int E = Integer.parseInt(st.nextToken());
		int startV = Integer.parseInt(in.readLine());
		
		Map<Integer, Integer>[] list = new HashMap[V];
		for (int i = 1; i < V; i++)
			list[i] = new HashMap<>();
		int[] weights = new int[V];
		boolean[] visited = new boolean[V];
		visited[0] = true;	// 0 index는 사용하지 않음
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[from].put(to, weight);
		}
		
		Arrays.fill(weights, Integer.MAX_VALUE);
		weights[startV] = 0;	// 시작점부터 시작하도록 처리
		
		for (int i = 1; i < V; i++) {	// 모든 정점이 포함될 때까지 순회
			// 가장 가까운 거리 선택하기
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			for (int j = 1; j < V; j++) {	// j번째 경로까지의 거리를 확인
				if (visited[j])
					continue;
				if (weights[j] < min) {
					min = weights[j];
					minVertex = j;
				}
			}
			
			if (minVertex == -1)
				break;
			
			visited[minVertex] = true;
			
			// 선택한 정점을 통한 경로의 가중치 체크 (startV ~ minVertex ~ j)
			for (int j = 1; j < V; j++) {
				if (visited[j])
					continue;
				
				if (list[minVertex].containsKey(j) && weights[j] > weights[minVertex] + list[minVertex].get(j))
					weights[j] = (short)(weights[minVertex] + list[minVertex].get(j));
			}
		}
		
		for (int i = 1; i < V; i++) {
			if (weights[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(weights[i]);
		}
	}
}

/* dijkstra : 메모리 초과
public static void main(String[] args) throws IOException {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(in.readLine());
	
	int V = Integer.parseInt(st.nextToken()) + 1;
	int E = Integer.parseInt(st.nextToken());
	int startV = Integer.parseInt(in.readLine());
	
	short[][] map = new short[V][V];
	short[] weights = new short[V];
	boolean[] visited = new boolean[V];
	visited[0] = true;	// 0 index는 사용하지 않음
	
	for (int i = 0; i < E; i++) {
		st = new StringTokenizer(in.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		short weight = (short)Integer.parseInt(st.nextToken());
		
		if (map[from][to] != 0 && map[from][to] < weight)
			continue;
			
		map[from][to] = weight;
	}
	
	Arrays.fill(weights, Short.MAX_VALUE);
	weights[startV] = 0;	// 시작점부터 시작하도록 처리
	
	for (int i = 1; i < V; i++) {	// 모든 정점이 포함될 때까지 순회
		// 가장 가까운 거리 선택하기
		int min = Short.MAX_VALUE;
		int minVertex = -1;
		for (int j = 1; j < V; j++) {	// j번째 경로까지의 거리를 확인
			if (visited[j])
				continue;
			if (weights[j] < min) {
				min = weights[j];
				minVertex = j;
			}
		}
		
		if (minVertex == -1)
			break;
		
		visited[minVertex] = true;
		
		// 선택한 정점을 통한 경로의 가중치 체크 (startV ~ minVertex ~ j)
		for (int j = 1; j < V; j++) {
			if (visited[j])
				continue;
			if (map[minVertex][j] != 0 && weights[j] > weights[minVertex] + map[minVertex][j])
				weights[j] = (short)(weights[minVertex] + map[minVertex][j]);
		}
	}
	
	for (int i = 1; i < V; i++) {
		if (weights[i] == Short.MAX_VALUE)
			System.out.println("INF");
		else
			System.out.println(weights[i]);
	}
}
*/