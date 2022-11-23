// https://www.acmicpc.net/problem/1865

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1865_Wormhole {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int INF = 100_000_000;
		
		int TC = Integer.parseInt(in.readLine());
		for (int t = 0; t < TC; t++) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			int[][] road = new int[M * 2 + W][3];
			
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(in.readLine());
				int S = Integer.parseInt(st.nextToken()) - 1;
				int E = Integer.parseInt(st.nextToken()) - 1;
				int T = Integer.parseInt(st.nextToken());
				
				road[m * 2][0] = S;
				road[m * 2][1] = E;
				road[m * 2][2] = T;
				road[m * 2 + 1][0] = S;
				road[m * 2 + 1][1] = E;
				road[m * 2 + 1][2] = T;
			}

			for (int w = 0; w < W; w++) {
				st = new StringTokenizer(in.readLine());
				int S = Integer.parseInt(st.nextToken()) - 1;
				int E = Integer.parseInt(st.nextToken()) - 1;
				int T = Integer.parseInt(st.nextToken());
				
				road[M * 2 + w][0] = S;
				road[M * 2 + w][1] = E;
				road[M * 2 + w][2] = -T;
			}
			
			long[] dist = new long[N];
			Arrays.fill(dist, INF);
			dist[0] = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < road.length; j++) {
					if (dist[road[j][1]] > dist[road[j][0]] + road[j][2])
						dist[road[j][1]] = dist[road[j][0]] + road[j][2];
				}
			}
			
			boolean isCycle = false;
			for (int i = 0; i < road.length; i++) {
				if (dist[road[i][1]] > dist[road[i][0]] + road[i][2]) {
					isCycle = true;
					break;
				}
			}
			
			System.out.println(isCycle ? "YES" : "NO");
		}
	}
}