// https://www.acmicpc.net/problem/17143

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17143_FishingKing {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int[] dy = { 0, -1, 1, 0, 0 };
		int[] dx = { 0, 0, 0, 1, -1 };
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		Shark[] sharks = new Shark[S+1];	// 0번째 상어는 없음
		
		for (int i = 1; i <= S; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());	// 방향: 1위 2아래 3오른쪽 4왼쪽
			int z = Integer.parseInt(st.nextToken());
			
			map[r][c] = i;
			if (d == 1 || d == 2) s %= (R-1) * 2; 
			else if (d == 3 || d == 4) s %= (C-1) * 2; 
			sharks[i] = new Shark(r, c, s, d, z);
		}
		
		int sum = 0;
		for (int fisher = 0; fisher < C; fisher++) {
			
			// 상어 낚시
			for (int r = 0; r < R; r++) {
				if (map[r][fisher] > 0) {
					int fished = map[r][fisher];
					sum += sharks[fished].size;
					map[r][fisher] = 0;
					sharks[fished] = null;
					break;
				}
			}
			
			// 맵을 비워줌
			for (int s = 1; s <= S; s++) {
				if (sharks[s] == null)
					continue;
				map[sharks[s].r][sharks[s].c] = 0;
			}
			
			// 상어 이동
			for (int s = 1; s <= S; s++) {
				if (sharks[s] == null)
					continue;
				
				Shark shark = sharks[s];
				shark.r += dy[shark.dir] * shark.speed;
				shark.c += dx[shark.dir] * shark.speed;
				
				boolean turned = false;
				while (shark.r < 0 || shark.r >= R) {
					if (shark.r < 0) {
						shark.r = Math.abs(shark.r);
						turned = !turned;
					}
					if (shark.r >= R) {
						shark.r = R-1 - (shark.r - (R-1));
						turned = !turned;
					}
				}
				while (shark.c < 0 || shark.c >= C) {
					if (shark.c < 0) {
						shark.c = Math.abs(shark.c);
						turned = !turned;
					}
					if (shark.c >= C) {
						shark.c = C-1 - (shark.c - (C-1));
						turned = !turned;
					}
				}
				
				// 방향이 바뀌는 경우
				if (turned) {
					if (shark.dir % 2 == 0) shark.dir--;
					else shark.dir++;
				}
				
				// 상어 잡아먹힘
				if (map[shark.r][shark.c] > 0) {
					if (sharks[map[shark.r][shark.c]].size < shark.size) {
						sharks[map[shark.r][shark.c]] = null;
						map[shark.r][shark.c] = s;
					}
					else {
						sharks[s] = null;
					}
				}
				else
					map[shark.r][shark.c] = s;
			}
		}
		
		System.out.println(sum);
	}
	
	static class Shark {
		int r, c, speed, dir, size;
		public Shark(int r, int c, int speed, int dir, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
	}
}