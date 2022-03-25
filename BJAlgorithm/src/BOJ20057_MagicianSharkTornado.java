// https://www.acmicpc.net/problem/20057

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057_MagicianSharkTornado {
	
	static int[][] map;
	static int outCnt, n, totalSand;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(in.readLine());
		map = new int[n][n];
		outCnt = 0;
		
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(in.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ny = n / 2;
		int nx = n / 2;
		int mv = 1;
		
		here: while (true) {
			if (mv % 2 == 1) {
				for (int i = 0; i < mv; i++) {
					nx--;
					if (nx < 0) break here;
					totalSand = map[ny][nx];
					int sand = map[ny][nx];
					if (sand > 9) {
						tornado(ny-2, nx, sand, 0.02);
						tornado(ny-1, nx-1, sand, 0.1);
						tornado(ny-1, nx, sand, 0.07);
						tornado(ny-1, nx+1, sand, 0.01);
						tornado(ny, nx-2, sand, 0.05);
						tornado(ny+1, nx-1, sand, 0.1);
						tornado(ny+1, nx, sand, 0.07);
						tornado(ny+1, nx+1, sand, 0.01);
						tornado(ny+2, nx, sand, 0.02);
					}
					if (nx-1 >= 0)
						map[ny][nx-1] += totalSand;
					else outCnt += totalSand;
					map[ny][nx] = 0;
				}
				for (int i = 0; i < mv; i++) {
					ny++;
					totalSand = map[ny][nx];
					int sand = map[ny][nx];
					if (sand > 9) {
						tornado(ny, nx-2, sand, 0.02);
						tornado(ny+1, nx-1, sand, 0.1);
						tornado(ny, nx-1, sand, 0.07);
						tornado(ny-1, nx-1, sand, 0.01);
						tornado(ny+2, nx, sand, 0.05);
						tornado(ny+1, nx+1, sand, 0.1);
						tornado(ny, nx+1, sand, 0.07);
						tornado(ny-1, nx+1, sand, 0.01);
						tornado(ny, nx+2, sand, 0.02);
					}
					if (ny+1 < n)
						map[ny+1][nx] += totalSand;
					else outCnt += totalSand;
					map[ny][nx] = 0;
				}
				mv++;
			}
			else {
				for (int i = 0; i < mv; i++) {
					nx++;
					totalSand = map[ny][nx];
					int sand = map[ny][nx];
					if (sand > 9) {
						tornado(ny-2, nx, sand, 0.02);
						tornado(ny-1, nx+1, sand, 0.1);
						tornado(ny-1, nx, sand, 0.07);
						tornado(ny-1, nx-1, sand, 0.01);
						tornado(ny, nx+2, sand, 0.05);
						tornado(ny+1, nx+1, sand, 0.1);
						tornado(ny+1, nx, sand, 0.07);
						tornado(ny+1, nx-1, sand, 0.01);
						tornado(ny+2, nx, sand, 0.02);
					}
					if (nx+1 < n)
						map[ny][nx+1] += totalSand;
					else outCnt += totalSand;
					map[ny][nx] = 0;
				}
				for (int i = 0; i < mv; i++) {
					ny--;
					totalSand = map[ny][nx];
					int sand = map[ny][nx];
					if (sand > 9) {
						tornado(ny, nx-2, sand, 0.02);
						tornado(ny-1, nx-1, sand, 0.1);
						tornado(ny, nx-1, sand, 0.07);
						tornado(ny+1, nx-1, sand, 0.01);
						tornado(ny-2, nx, sand, 0.05);
						tornado(ny-1, nx+1, sand, 0.1);
						tornado(ny, nx+1, sand, 0.07);
						tornado(ny+1, nx+1, sand, 0.01);
						tornado(ny, nx+2, sand, 0.02);
					}
					if (ny-1 >= 0)
						map[ny-1][nx] += totalSand;
					else outCnt += totalSand;
					map[ny][nx] = 0;
				}
				mv++;
			}
		}
		
		System.out.println(outCnt);
	}

	static void tornado(int y, int x, int curSand, double sandAmount) {
		int moveSand = (int)(curSand * sandAmount);
		if (y >= n || y < 0 || x >= n || x < 0)
			outCnt += moveSand;
		else
			map[y][x] += moveSand;
		totalSand -= moveSand;
	}
}

