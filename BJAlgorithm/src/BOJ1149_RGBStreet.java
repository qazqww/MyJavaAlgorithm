// https://www.acmicpc.net/problem/1149

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1149_RGBStreet {
	public static void main(String[] args) throws IOException {		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[][] prices = new int[N][3];	// { R, G, B }
		
		// 입력 부분
		for (int i = 0; i < N; i++) {
			String[] temp = in.readLine().split(" ");
			prices[i][0] = Integer.parseInt(temp[0]);
			prices[i][1] = Integer.parseInt(temp[1]);
			prices[i][2] = Integer.parseInt(temp[2]);
		}
		
		// 마지막으로 R, G, B를 칠한 경우의 비용을 담을 변수
		int R = 0;
		int G = 0;
		int B = 0;
		
		for (int i = 0; i < N; i++) {
			int tempR = prices[i][0] + Math.min(G, B);	// R을 칠할 수 있는 집(G, B)에 R(prices[i][0])을 칠하는 경우의 최소 비용
			int tempG = prices[i][1] + Math.min(R, B);
			int tempB = prices[i][2] + Math.min(R, G);
			R = tempR;
			G = tempG;
			B = tempB;
		}
		
		// 최종적으로 구한 값의 최소값이 정답
		int answer = Math.min(R, G);
		answer = Math.min(answer, B);
		
		System.out.println(answer);
		
	}
}