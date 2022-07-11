// https://www.acmicpc.net/problem/14889

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889_StartLink {
	
	static int N, cnt, answer;
	static int[][] synergy;
	static boolean[] chosen;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		cnt = 0;
		answer = Integer.MAX_VALUE;
		
		StringTokenizer st;
		synergy = new int[N][N];
		chosen = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(in.readLine());
			 for (int j = 0; j < N; j++) {
				 synergy[i][j] = Integer.parseInt(st.nextToken());
			 }
		}
		
		combi(0);
		
		System.out.println(answer);
	}
	
	static void combi(int num) {
		
		if (cnt == N/2) {
			
			int teamStart = 0;
			int teamLink = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (chosen[i] && chosen[j])
						teamStart += synergy[i][j] + synergy[j][i];
					else if (!chosen[i] && !chosen[j])
						teamLink += synergy[i][j] + synergy[j][i];
				}
			}
			
			answer = Math.min(answer, Math.abs(teamStart - teamLink));
			return;
		}
		
		for (int i = num; i < N; i++) {
			chosen[i] = true;
			cnt++;
			combi(i+1);
			chosen[i] = false;
			cnt--;
		}
	}
}