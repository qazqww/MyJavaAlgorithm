// https://www.acmicpc.net/problem/3190

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190_Snake {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		int appleNum = Integer.parseInt(in.readLine());
		
		int[][] board = new int[N][N]; // 0: 빈 칸, 1: 뱀, 9: 사과
		
		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };
		int d = 1;
		
		for (int i = 0; i < appleNum; i++) {
			st = new StringTokenizer(in.readLine());
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 9;
		}
		
		int rotateNum = Integer.parseInt(in.readLine());
		Map<Integer, Boolean> rotateInfo = new HashMap<>(); // true가 오른쪽
		for (int i = 0; i < rotateNum; i++) {
			st = new StringTokenizer(in.readLine());
			rotateInfo.put(Integer.parseInt(st.nextToken()), st.nextToken().equals("D") ? true : false);
		}

		int r = 0;
		int c = 0;
		int time = 0;
		board[r][c] = 1;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { r, c });
		
		while (true) {
			time++;
			
			if (rotateInfo.containsKey(time - 1)) {
				if (rotateInfo.get(time - 1))
					d = (d == 3) ? 0 : d + 1;
				else {
					d = (d == 0) ? 3 : d - 1;
				}
			}
			
			r += dy[d];
			c += dx[d];
			
			if (r >= N || r < 0 || c >= N || c < 0 || board[r][c] == 1) {
				break;
			}

			if (board[r][c] != 9) {
				int[] del = queue.poll();
				board[del[0]][del[1]] = 0;
			}
			
			board[r][c] = 1;
			queue.offer(new int[] { r, c });
		}
		
		System.out.println(time);
	}
}