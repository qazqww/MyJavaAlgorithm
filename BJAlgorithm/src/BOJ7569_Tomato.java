
// https://www.acmicpc.net/problem/7569

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569_Tomato {
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int z = Integer.parseInt(st.nextToken());

		int[] dx = { -1, 1, 0, 0, 0, 0 };
		int[] dy = { 0, 0, -1, 1, 0, 0 };
		int[] dz = { 0, 0, 0, 0, -1, 1 };

		Queue<int[]> queue = new LinkedList<>();
		int day = 0;
		int counted = 0;
		int target = x * y * z;

		int[][][] tmts = new int[x][y][z];
		for (int k = 0; k < z; k++) {
			for (int j = 0; j < y; j++) {
				st = new StringTokenizer(in.readLine());
				for (int i = 0; i < x; i++) {
					tmts[i][j][k] = Integer.parseInt(st.nextToken());
					if (tmts[i][j][k] == 1) {
						queue.offer(new int[] { i, j, k });
						counted++;
					} else if (tmts[i][j][k] == -1) {
						target--;
					}
				}
			}
		}
		while (!queue.isEmpty()) {
			int qSize = queue.size();
			for (int d = 0; d < qSize; d++) {
				int[] now = queue.poll();
				int ax = now[0];
				int ay = now[1];
				int az = now[2];

				for (int j = 0; j < 6; j++) {
					int nx = ax + dx[j];
					int ny = ay + dy[j];
					int nz = az + dz[j];

					if (nx >= x || nx < 0 || ny >= y || ny < 0 || nz >= z || nz < 0 || tmts[nx][ny][nz] != 0) {
						continue;
					}

					counted++;
					tmts[nx][ny][nz] = 1;
					queue.offer(new int[] { nx, ny, nz });
				}
			}
			
			if (!queue.isEmpty())
				day++;
		}
		
		
		if (counted == target)
			System.out.println(day);
		else
			System.out.println(-1);
	}
}
