// https://www.acmicpc.net/problem/2239

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2239_Sudoku {
	
	static int[][] board = new int[9][9];
	static boolean[][] horizontal = new boolean[9][10];	// i번째 가로줄의 숫자 존재 여부
	static boolean[][] vertical = new boolean[9][10];	// i번째 세로줄의 숫자 존재 여부
	static boolean[][] section = new boolean[9][10];	// i번째 구역의 숫자 존재 여부
	static ArrayList<Point> empty = new ArrayList<>();	// 빈 칸의 위치 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int r = 0; r < 9; r++) {
			String temp = in.readLine();
			for (int c = 0; c < 9; c++) {
				// 숫자 받아와 입력
				board[r][c] = temp.charAt(c) - '0';
				
				// 숫자 중복 방지를 위한 구역별 숫자 존재 여부 체크
				int num = board[r][c];
				horizontal[r][num] = true;
				vertical[c][num] = true;
				section[getSection(r, c)][num] = true;
				
				// 빈 칸이라면 리스트에 추가
				if (num == 0)
					empty.add(new Point(r, c));
			}
		}
		
		fill(0);	// 첫 빈 칸부터 시작
		
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				System.out.print(board[r][c]);
			}
			System.out.println();
		}
	}
	
	// 빈 칸을 점차 채워나가는 재귀 메서드
	static boolean fill(int index) {
		if (index == empty.size())	// 빈 칸을 모두 채웠다면 종료
			return true;
			
		Point p = empty.get(index);	// index번째의 빈 칸을 받아와서
		for (int n = 1; n < 10; n++) {	// 1부터 9까지 가능한 수를 채워본다
			
			// 가로, 세로, 사각형에 중복된 수가 있다면 패스
			if (horizontal[p.r][n] || vertical[p.c][n] || section[getSection(p.r, p.c)][n])
				continue;
			
			horizontal[p.r][n] = true;
			vertical[p.c][n] = true;
			section[getSection(p.r, p.c)][n] = true;
			board[p.r][p.c] = n;
			
			if(fill(index+1))	// 다음 빈 칸으로 진행
				return true;
			
			horizontal[p.r][n] = false;
			vertical[p.c][n] = false;
			section[getSection(p.r, p.c)][n] = false;
			board[p.r][p.c] = 0;
		}
		
		return false;
	}
	
	// 몇 번째 사각형 구역에 해당하는지 알려주는 메서드
	static int getSection(int r, int c) {
		if (r < 3) {
			if (c < 3)
				return 0;
			else if (c < 6)
				return 1;
			else
				return 2;
		}
		else if (r < 6) {
			if (c < 3)
				return 3;
			else if (c < 6)
				return 4;
			else
				return 5;
		}
		else {
			if (c < 3)
				return 6;
			else if (c < 6)
				return 7;
			else
				return 8;
		}
	}
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}