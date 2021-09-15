// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJAVpqrzQDFAWr

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3282_01Knapsack {
	public static void main(String[] args) throws IOException {		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			int N = Integer.parseInt(st.nextToken());	// 물건의 개수
			int W = Integer.parseInt(st.nextToken());	// 가방의 부피
			
			int[][] items = new int[N][2];	// 물건의 { 부피, 가치 }
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				items[i][0] = Integer.parseInt(st.nextToken());
				items[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int[][] table = new int[N][W+1];

			for (int i = 0; i < N; i++) {
				for (int w = 0; w <= W; w++) {
					if (i == 0) {	// 첫 줄은 가능한 부피라면 그냥 대입
						if (w >= items[i][0]) table[i][w] = items[i][1];
					}
					else {
						if (w >= items[i][0])
							table[i][w] = Math.max(table[i-1][w], items[i][1] + table[i-1][w - items[i][0]]);
						else
							table[i][w] = table[i-1][w];
					}
				}
			}
			
			System.out.printf("#%d %d\n", t+1, table[N-1][W]);
		}
	}
}