
// https://www.acmicpc.net/problem/14503

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503_RobotCleaner {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine());
		int nowR = Integer.parseInt(st.nextToken());
		int nowC = Integer.parseInt(st.nextToken());
		int nowDir = Integer.parseInt(st.nextToken()); // {북, 동, 남, 서}

		int count = 0;

		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		map[nowR][nowC] = 9; // 시작 위치 청소
		count++;

		while (true) {
			if ((nowR - 1 < 0 || map[nowR - 1][nowC] != 0) && (nowR + 1 >= N || map[nowR + 1][nowC] != 0) && (nowC - 1 < 0
					|| map[nowR][nowC - 1] != 0) && (nowC + 1 >= M || map[nowR][nowC + 1] != 0)) { // 사방 청소 불가
				int backR = 0;
				int backC = 0;
				switch (nowDir) {
				case 0:
					backR = 1;
					break;
				case 1:
					backC = -1;
					break;
				case 2:
					backR = -1;
					break;
				case 3:
					backC = 1;
					break;
				}

				if (nowR + backR < 0 || nowR + backR >= N || nowC + backC < 0 || nowC + backC >= M
						|| map[nowR + backR][nowC + backC] == 1) { // 후진 불가
					break;
				} else {
					nowR += backR;
					nowC += backC;
					continue;
				}
			}

			int leftR = 0;
			int leftC = 0;
			switch (nowDir) {
			case 0:
				leftC = -1;
				break;
			case 1:
				leftR = -1;
				break;
			case 2:
				leftC = 1;
				break;
			case 3:
				leftR = 1;
				break;
			}

			nowDir = (nowDir == 0) ? 3 : nowDir - 1; // 왼쪽으로 회전
			
			if (nowR + leftR < 0 || nowR + leftR >= N || nowC + leftC < 0 || nowC + leftC >= M
					|| map[nowR + leftR][nowC + leftC] != 0) { // 왼쪽 청소 불가
				continue;
			} else {
				nowR += leftR;
				nowC += leftC;
				map[nowR][nowC] = 9;
				count++;
			}
		}

		System.out.println(count);
	}
}
