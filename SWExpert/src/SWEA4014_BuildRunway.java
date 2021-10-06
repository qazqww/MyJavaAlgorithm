// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4014_BuildRunway {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());

			int[][] map = new int[N][N];
			int answer = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 세로 살펴보기
			for (int r = 0; r < N; r++) {
				boolean canBuild = true; // 경사로를 설치할 수 있는가
				boolean[] used = new boolean[N]; // 경사로를 설치한 위치인지 체크

				for (int c = 0; c < N - 1; c++) { // 한 구간 한 구간 훑어보기
					if (map[r][c] != map[r][c + 1]) { // 높이가 다른 구간이 있다면

						// 앞, 뒤 중 어느 곳이 낮은지 확인
						if (map[r][c] == map[r][c + 1] - 1) { // 앞이 더 낮은 경우

							if (c - X + 1 < 0) // 경사로 설치 범위가 맵을 벗어난다면 실패
								canBuild = false;

							else {
								int height = map[r][c]; // 낮은 위치의 시작점을 저장
								used[c] = true;
								for (int i = c - 1; i >= c - X + 1; i--) { // 경사로 길이만큼 체크

									// 평지가 아니어서 설치할 수 없거나, 이미 다른 경사로가 있다면 실패
									if (map[r][i] != height || used[i]) {
										canBuild = false;
										break;
									}

									used[i] = true;
								}
							}
						} else if (map[r][c] - 1 == map[r][c + 1]) { // 뒤가 더 낮은 경우
							if (c + X >= N)
								canBuild = false;
							else {
								int height = map[r][c + 1];
								used[c + 1] = true;
								for (int i = c + 2; i <= c + X; i++) {
									if (map[r][i] != height || used[i]) {
										canBuild = false;
										break;
									}
									used[i] = true;
								}
							}
						} else { // 높이 차이가 1보다 크다면 실패
							canBuild = false;
						}
					}
				}
				if (canBuild)
					answer++;
			}

			// 가로 살펴보기
			for (int c = 0; c < N; c++) {
				boolean canBuild = true;
				boolean[] used = new boolean[N];
				for (int r = 0; r < N - 1; r++) {
					if (map[r][c] != map[r + 1][c]) {
						if (map[r][c] == map[r + 1][c] - 1) {
							if (r - X + 1 < 0)
								canBuild = false;
							else {
								int height = map[r][c];
								used[r] = true;
								for (int i = r - 1; i >= r - X + 1; i--) {
									if (map[i][c] != height || used[i]) {
										canBuild = false;
										break;
									}
									used[i] = true;
								}
							}
						} else if (map[r][c] - 1 == map[r + 1][c]) {
							if (r + X >= N)
								canBuild = false;
							else {
								int height = map[r + 1][c];
								used[r + 1] = true;
								for (int i = r + 2; i <= r + X; i++) {
									if (map[i][c] != height || used[i]) {
										canBuild = false;
										break;
									}
									used[i] = true;
								}
							}
						} else {
							canBuild = false;
						}
					}
				}
				if (canBuild)
					answer++;
			}
			System.out.printf("#%d %d\n", t + 1, answer);
		}
	}
}