// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LuHfqDz8DFAXc

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1865_JobDistribute {
	
	static int N;
	static double result, max;
	static int[][] percent;
	static boolean[] isUsed;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			result = 1;
			max = 0;
			percent = new int[N][N];
			isUsed = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					percent[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			permu(0);
			
			System.out.println(String.format("#%d %.6f", t+1, max * 100));
		}
	}
	
	static void permu(int index) {
		
		if (index == N) {
			max = Math.max(result, max);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isUsed[i] || percent[index][i] == 0)
				continue;
			
			result = result * (percent[index][i] / 100.0);
			isUsed[i] = true;
			if (result >= max)
				permu(index+1);
			isUsed[i] = false;
			result = result / (percent[index][i] / 100.0);
		}
	}
}
