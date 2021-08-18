// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cook_4012 {
	
	static int N;				// 재료의 개수
	static int gap;				// 가장 적은 맛의 차이 수치를 저장
	static int[][] foodMap;		// 재료 시너지 정보를 입력받는 맵
	static int[] food1;			// 음식 1에 들어가는 재료
	static int[] food2;			// 음식 2에 들어가는 재료
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			
			N = Integer.parseInt(in.readLine());
			gap = Integer.MAX_VALUE;
			foodMap = new int[N][N];
			food1 = new int[N/2];
			food2 = new int[N/2];
			
			// 재료 시너지 정보를 입력받는 부분
			for (int y = 0; y < N; y++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int x = 0; x < N; x++) {
					foodMap[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			Combi(0, 0);
			System.out.printf("#%d %d\n", t+1, gap);
		}
	}
	
	// 음식 1에 넣을 수 있는 조합을 구함
	static void Combi(int index, int k) {
		
		// 조합이 완성되면 시너지를 계산하여 차이를 비교
		if (index == N/2) {
			
			int f2index = 0;
			boolean[] ingredients = new boolean[N];		// 재료 index를 나열한 임시 배열 생성
			for (int i : food1) {
				ingredients[i] = true;				// 음식 1에 들어간 (조합으로 완성한) 재료는 true로 표시
			}
			
			for (int i = 0; i < N; i++) {
				if (!ingredients[i])		// 1로 표시되지 않은 재료는 음식 2에 넣는다
					food2[f2index++] = i;
			}

			int f1value = 0;	// 음식 1의 시너지값
			int f2value = 0;	// 음식 2의 시너지값
			
			// 시너지 맵에서 재료에 해당하는 시너지를 취하여 더한다 
			for (int y : food1) {
				for (int x : food1) {
					f1value += foodMap[y][x];
				}
			}
			for (int y : food2) {
				for (int x : food2) {
					f2value += foodMap[y][x];
				}
			}
			
			int newGap = Math.abs(f1value - f2value);	// 기존에 구했던 차이값보다 적은지 확인한다
			if (newGap < gap)
				gap = newGap;
			
			return;
		}
		
		// 조합을 구하는 부분
		for (int i = k; i < N; i++) {
			food1[index] = i;
			Combi(index+1, i+1);
		}
	}
}