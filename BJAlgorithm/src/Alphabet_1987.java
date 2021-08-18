// https://www.acmicpc.net/problem/1987

import java.util.Scanner;

public class Alphabet_1987 {
	
	static int R, C;
	static int answer = 0;
	static int[] dr = new int[] { -1, 1, 0, 0 };
	static int[] dc = new int[] { 0, 0, -1, 1 };
	static boolean[] alphabets = new boolean[26];	// A ~ Z 까지에 해당하는 배열. 지나온 길에 있었으면 true로 체크
	static char[][] map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 맵 크기 설정과 입력
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		
		for (int r = 0; r < R; r++) {
			String temp = sc.next();
			for (int c = 0; c < C; c++) {
				map[r][c] = temp.charAt(c);
			}
		}

		// 첫 칸부터 시작하여 DFS 실행
		alphabets[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		
		System.out.println(answer);
	}
	
	static void dfs(int r, int c, int len) {
		for (int i = 0; i < 4; i++) {	// 사방 탐색
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < R && nc >= 0 && nc < C) {	// 범위를 벗어나지 않는지 확인
				
				if (!alphabets[map[nr][nc] - 'A']) {		// 지나온 길에 알파벳이 없었으면 이동 (false)
					alphabets[map[nr][nc] - 'A'] = true;	// 이동한 위치의 알파벳에 방문 표시
					dfs(nr, nc, len + 1);					// 그 자리에서 다시 DFS 사방탐색
					alphabets[map[nr][nc] - 'A'] = false;	// 탐색이 끝났으면 다시 false로
				}
			}
		}
		
		if (len > answer)	// 구한 칸 수보다 더 많이 갈 수 있으면 갱신
			answer = len;
	}
}