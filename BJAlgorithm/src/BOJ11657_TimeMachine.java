// https://www.acmicpc.net/problem/11657

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11657_TimeMachine {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		final int INF = 10_000_000;
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] dist = new long[N];
		int[][] bus = new int[M][3];
		Arrays.fill(dist, INF);
		dist[0] = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int time = Integer.parseInt(st.nextToken());
			
			bus[i] = new int[] { start, end, time };
		}
		
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (dist[bus[m][0]] != INF && dist[bus[m][1]] > dist[bus[m][0]] + bus[m][2])
					dist[bus[m][1]] = dist[bus[m][0]] + bus[m][2];
			}
		}
		
		boolean isCycle = false;
		for (int m = 0; m < M; m++) {
			if (dist[bus[m][0]] != INF && dist[bus[m][1]] > dist[bus[m][0]] + bus[m][2]) {
				isCycle = true;
				break;
			}
		}
		

		if (isCycle) {
			System.out.println(-1);
		}
		else {
			for (int i = 1; i < N; i++) {
				System.out.println(dist[i] >= INF ? -1 : dist[i]);
			}
		}
	}
}