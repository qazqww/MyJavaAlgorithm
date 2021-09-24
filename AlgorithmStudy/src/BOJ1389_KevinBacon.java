// https://www.acmicpc.net/problem/1389

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1389_KevinBacon {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int M = Integer.parseInt(temp[1]);
		
		// 그래프 생성
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) continue;	// 자기 자신은 0으로 두고
				map[i][j] = 100_000;	// 연결되지 않은 경우는 큰 수로 설정
			}
		}
		
		// 그래프 입력 부분
		for (int i = 0; i < M; i++) {
			temp = in.readLine().split(" ");
			int from = Integer.parseInt(temp[0]) - 1;
			int to = Integer.parseInt(temp[1]) - 1;
			map[from][to] = 1;
			map[to][from] = 1;
		}
		
		// 플로이드-와샬 알고리즘 사용
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > map[i][k] + map[k][j])
						map[i][j] = map[i][k] + map[k][j];
				}
			}
		}
		
		int min = Integer.MAX_VALUE;	// 가장 작은 케빈 베이컨 수
		int minPerson = -1;				// 가장 작은 케빈수를 가진 사람
		int kevinNum = 0;				// 케빈 수를 계산하여 임시로 담음
		for (int i = 0; i < N; i++) {
			kevinNum = 0;
			for (int j = 0; j < N; j++)	// 그래프 행렬의 가로줄을 더하여 한 사람의 케빈 수를 구한다
				kevinNum += map[i][j];
			
			if (kevinNum < min) {	// 원래 구했던 최소값보다 작을 경우 갱신
				min = kevinNum;
				minPerson = i+1;
			}
		}
		
		System.out.println(minPerson);
	}
}