// https://www.acmicpc.net/problem/20056

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056_MagicianSharkAndFireball {
	static final int[] dy = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] dx = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<Fireball>[][] map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int column = Integer.parseInt(st.nextToken()) - 1;
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			map[row][column].add(new Fireball(mass, speed, dir, false));
		}
		
		int answer = 0;
		for (int i = 0; i < K; i++) {
			// 이동
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!map[r][c].isEmpty()) {
						for (int j = map[r][c].size() - 1; j >= 0; j--) {
							Fireball f = map[r][c].get(j);
							if (f.isMoved)
								continue;
							
							map[r][c].remove(j);
							int ny = r + dy[f.dir] * f.speed;
							while (ny >= N) {
								ny -= N;
							}
							while (ny < 0) {
								ny += N;
							}
							int nx = c + dx[f.dir] * f.speed;
							while (nx >= N) {
								nx -= N;
							}
							while (nx < 0) {
								nx += N;
							}
							map[ny][nx].add(f);
							f.isMoved = true;
						}
					}
				}
			}
			// 합체
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c].size() > 1) {
						int newMass = 0;
						int newSpeed = 0;
						int newDir = 0;
						int size = map[r][c].size();
						for (Fireball f : map[r][c]) {							
							newMass += f.mass;
							newSpeed += f.speed;
							newDir = (f.dir % 2 == 0) ? newDir + 1 : newDir;
						}

						newMass /= 5;
						newSpeed /= size;
						map[r][c] = new ArrayList<>();
						
						if (newMass > 0) {
							if (newDir == 0 || newDir == size) {
								for (int d = 0; d < 8; d += 2) {
									map[r][c].add(new Fireball(newMass, newSpeed, d, true));
								}
							}
							else {
								for (int d = 1; d < 8; d += 2) {
									map[r][c].add(new Fireball(newMass, newSpeed, d, true));
								}
							}
						}
						
						
					}
				}
			}
			// 이동 여부 초기화 및 답 구하기
			answer = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!map[r][c].isEmpty()) {
						for (Fireball f : map[r][c]) {
							f.isMoved = false;
							answer += f.mass;
						}
					}
				}
			}
		}
		System.out.println(answer);
	}
	
	static class Fireball {
		int mass;
		int speed;
		int dir;
		boolean isMoved;
		public Fireball(int mass, int speed, int dir, boolean isMoved) {
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
			this.isMoved = isMoved;
		}
	}
}