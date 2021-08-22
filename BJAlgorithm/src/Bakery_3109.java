// https://www.acmicpc.net/problem/3109

// DFS의 종료 조건(boolean 대신 if~else 만으로 해결하려 함)을 제대로 생각하지 못하여 헤맸던 문제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bakery_3109 {
	
	static int R, C, answer;
	static char[][] map;
	static boolean found;		// 파이프를 완성했을 경우 탐색을 멈추도록 boolean을 이용
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		map = new char[R][C];
		
		for (int r = 0; r < R; r++) {
			map[r] = in.readLine().toCharArray();
		}
		
		for (int r = 0; r < R; r++) {
			found = false;
			findPath(r, 0);
		}
		
		System.out.println(answer);
	}
	
	// DFS를 이용하여 파이프가 들어갈 자리 탐색
	static void findPath(int r, int c) {
		map[r][c] = 'p';	// 파이프 설치 가능 여부를 탐색한 자리는 표시 => 이 자리는 다음에 사용할 일이 없음
		
		if (c == C-1) {
			answer++;
			found = true;
			return;
		}
		
		if (r-1 >= 0 && map[r-1][c+1] == '.') {		// 우상 방향 탐색
			findPath(r-1, c+1);
		}
		if (!found && map[r][c+1] == '.') {		// 우 방향 탐색
			findPath(r, c+1);
		}
		if (!found && r+1 < R && map[r+1][c+1] == '.') {	// 우하 방향 탐색
			findPath(r+1, c+1);
		}
	}
}