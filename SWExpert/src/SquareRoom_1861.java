// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LtJYKDzsDFAXc

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SquareRoom_1861 {
	
	static int N;
	static int min, max;	// 연속된 수 탐색 중 min값과 max값
	static int[][] arr;		// 숫자의 방을 담을 배열
	static boolean[] used;	// 사용된 수를 처리

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			int start = Integer.MAX_VALUE;
			int length = -1;
			
			N = Integer.parseInt(in.readLine());
			arr = new int[N][N];
			used = new boolean[N*N + 1];
			
			for (int y = 0; y < N; y++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int x = 0; x < N; x++) {
					arr[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (!used[arr[y][x]]) {		// 사용되지 않은 수라면
						min = arr[y][x];
						max = arr[y][x];
						explore(y, x, -1);		// 1 작은 수들을 탐색 => min에 대입
						explore(y, x, 1);		// 1 큰 수들을 탐색 => max에 대입
						
						if (max - min + 1 > length) {	// 길이가 가장 큰 것으로 구하기
							length = max - min + 1;
							start = min;
						}
						else if (max - min + 1 == length && min < start) {	// 길이가 같을 경우, 시작점이 작은 수로 구하기
							start = min;
						}
					}
				}
			}
			
			System.out.printf("#%d %d %d\n", t+1, start, length);
		}
	}
	
	// 사방 탐색을 수행하는 재귀 메서드
	static void explore(int y, int x, int find) {	// 현재 좌표, 찾아야 하는 수(+1, -1)를 넘김
		boolean isFound = false;
		used[arr[y][x]] = true;
		if (!isFound && y - 1 >= 0) {
			if (arr[y - 1][x] == arr[y][x] + find) {
				isFound = true;
				explore(y-1, x, find);
			}
		}
		if (!isFound && y + 1 < N) {
			if (arr[y + 1][x] == arr[y][x] + find) {
				isFound = true;
				explore(y+1, x, find);
			}			
		}
		if (!isFound && x - 1 >= 0) {
			if (arr[y][x - 1] == arr[y][x] + find) {
				isFound = true;
				explore(y, x - 1, find);
			}
		}
		if (!isFound && x + 1 < N) {
			if (arr[y][x + 1] == arr[y][x] + find) {
				isFound = true;
				explore(y, x + 1, find);
			}
		}
		
		if (find == -1 && arr[y][x] < min)
			min = arr[y][x];
		if (find == 1 && arr[y][x] > max)
			max = arr[y][x];
	}
}
