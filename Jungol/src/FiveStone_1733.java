// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1006&sca=99&sfl=wr_hit&stx=1733

import java.util.Scanner;

public class FiveStone_1733 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] board = new int[19][19];
		
		for (int r = 0; r < 19; r++) {
			for (int c = 0; c < 19; c++) {
				board[r][c] = sc.nextInt();
			}
		}
		
		boolean isEnd = false;
		
		loop: for (int r = 0; r < 19; r++) {
			for (int c = 0; c < 19; c++) {
				if (board[r][c] != 0) {		// 돌이 존재하는 칸만 체크
					int num = board[r][c];
					
					// 우상 방향
					if (r > 3 && c < 15 && (r+1 >= 19 || c-1 < 0 || board[r+1][c-1] != num)) {	// 나아갈 방향의 이전 칸에 같은 돌이 있는지 체크
						for (int i = 1; i < 5; i++) {	// 해당 방향으로 4개의 칸을 더 체크
							if (board[r-i][c+i] != num)	// 같은 색의 돌이 없으면 오목 성립이 안되므로 체크 종료
								break;
							if (i == 4) {	// 돌이 연속 5개까지 존재했다면 6개가 아님을 체크
								if (r-5 < 0 || c+5 >= 19 || board[r-5][c+5] != num)	{ // 같은 색의 돌이 없거나 6개째를 둘 공간이 없는 경우
									System.out.printf("%d\n%d %d", num, r+1, c+1);
									isEnd = true;
									break loop;
								}
							}
						}
					}
					
					// 우 방향
					if (c < 15 && (c-1 < 0 || board[r][c-1] != num)) {
						for (int i = 1; i < 5; i++) {
							if (board[r][c+i] != num)
								break;
							if (i == 4) {
								if (c+5 >= 19 || board[r][c+5] != num) {
									System.out.printf("%d\n%d %d", num, r+1, c+1);
									isEnd = true;
									break loop;
								}
							}
						}
					}
					
					// 우하 방향
					if (r < 15 && c < 15 && (r-1 < 0 || c-1 < 0 || board[r-1][c-1] != num)) {
						for (int i = 1; i < 5; i++) {
							if (board[r+i][c+i] != num)
								break;
							if (i == 4) {
								if (r+5 >= 19 || c+5 >= 19 || board[r+5][c+5] != num) {
									System.out.printf("%d\n%d %d", num, r+1, c+1);
									isEnd = true;
									break loop;
								}
							}
						}
					}
					
					// 하 방향
					if (r < 15 && (r-1 < 0 || board[r-1][c] != num)) {
						for (int i = 1; i < 5; i++) {
							if (board[r+i][c] != num)
								break;
							if (i == 4) {
								if (r+5 >= 19 || board[r+5][c] != num) {
									System.out.printf("%d\n%d %d", num, r+1, c+1);
									isEnd = true;
									break loop;
								}
							}
						}
					}
				}
			}
		}
		
		if (!isEnd)
			System.out.println('0');
	}
}