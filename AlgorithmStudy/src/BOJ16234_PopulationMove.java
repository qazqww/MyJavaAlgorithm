// https://www.acmicpc.net/problem/16234

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ16234_PopulationMove {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int[] dy = new int[] { -1, 0, 1, 0 };
		int[] dx = new int[] { 0, 1, 0, -1 };

		int N = sc.nextInt();
		int L = sc.nextInt();
		int R = sc.nextInt();

		int[][] A = new int[N][N];
		int[][] allyNum;

		// 맵 입력
		for (int r = 0; r < N; r++)
			for (int c = 0; c < N; c++)
				A[r][c] = sc.nextInt();

		int day = 0;	// 날짜 경과를 체크
		
		// 인구 이동이 없을 때까지 계속 반복
		while (true) {
			int allyCnt = 0;			// 연합에 매기는 번호
			allyNum = new int[N][N];	// 구역마다 매겨진 연합 번호를 담는 맵
			boolean isMoved = false;	// 인구 이동이 일어났는지 체크 (종료 조건 체크)
			
			// 인구 이동을 계산하는 부분
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {

					if (allyNum[r][c] != 0)		// 연합 번호가 매겨졌다면 이미 탐색한 곳
						continue;

					allyCnt++;	// 새로운 연합 번호
					allyNum[r][c] = allyCnt;

					Queue<Point> explore = new LinkedList<>();	// 사방 탐색을 위한 큐
					explore.offer(new Point(r, c));

					while (!explore.isEmpty()) {
						Point p = explore.poll();
						for (int i = 0; i < 4; i++) {
							int ny = p.y + dy[i];
							int nx = p.x + dx[i];

							if (ny >= N || ny < 0 || nx >= N || nx < 0 || allyNum[ny][nx] != 0)
								continue;

							int gap = Math.abs(A[ny][nx] - A[p.y][p.x]);	// 인접한 국가의 인구 차이
							if (gap >= L && gap <= R) {				// 조건을 만족하면
								isMoved = true;						// 인구 이동이 일어났음을 체크
								allyNum[ny][nx] = allyCnt;			// 위치에 연합 번호를 매김
								explore.offer(new Point(ny, nx));	// 다음 탐색 후보로 저장
							}
						}
					}
				}
			}
			
			if (!isMoved)	// 인구 이동이 일어나지 않았을 경우 종료
				break;
			
			day++;		// 날짜의 경과를 표시

			int[] population = new int[allyCnt];				// 인구의 총 합
			ArrayList<Point>[] points = new ArrayList[allyCnt];	// 연합의 위치
			
			for (int i = 0; i < allyCnt; i++)
				points[i] = new ArrayList<>();

			// 연합 정리 => 위로 올리기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int num = allyNum[r][c] - 1;	// 0 index부터 채우기 위해 -1을 해줌
					points[num].add(new Point(r, c));	// 리스트에 위치 담기
					population[num] += A[r][c];			// 배열에 인구수 담기
				}
			}
			
			// 인구 이동 처리
			for (int i = 0; i < allyCnt; i++) {
				population[i] /= points[i].size();	// 연합의 인구수를 연합 칸 개수로 나눠 한 칸의 인구를 구한다
				for (int j = 0; j < points[i].size(); j++) {	// 한 연합의 위치를 순회
					Point p = points[i].get(j);
					A[p.y][p.x] = population[i];	// 구한 인구수를 덮어씌운다
				}
			}
		}
		
		System.out.println(day);
	}

	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}