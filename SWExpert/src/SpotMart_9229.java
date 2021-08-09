// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN

import java.util.Scanner;

public class SpotMart_9229 {

	static int cnt = 0;		// 과자 봉지 개수
	static int limit = 0;	// 무게 합 제한
	static int[] weight;	// 과자 무게
	static int sum = 0;		// 들고 있는 과자 무게의 합
	static int maxSum = -1;	// 최대 무게 합
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int t = 0; t < TC; t++) {
			
			// 입력받는 부분
			sum = 0;
			maxSum = -1;
			cnt = sc.nextInt();	
			limit = sc.nextInt();
			weight = new int[cnt];
			for (int i = 0; i < cnt; i++) {
				weight[i] = sc.nextInt();
			}
			
			// 조합 구하기
			Combi(0, 0);
			
			// 출력 부분
			System.out.printf("#%d %d\n", t+1, maxSum);
		}
	}
	
	static void Combi(int index, int num) {
		if (index == 2) {
			if (sum > maxSum) {		// 최대 무게인지 확인
				maxSum = sum;
			}
			return;
		}
		
		for (int i = num; i < cnt; i++) {
			sum += weight[i];
			if (sum > limit) {		// 무게 제한을 넘을 시엔 패스
				sum -= weight[i];
				continue;
			}
			Combi(index+1, i + 1);
			sum -= weight[i];
		}
	}
}

/* 이중 for문 사용 코드
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int TC = sc.nextInt();
	for (int t = 0; t < TC; t++) {
		int maxSum = -1;
		int cnt = sc.nextInt();	
		int limit = sc.nextInt();
		int[] weight = new int[cnt];
		for (int i = 0; i < cnt; i++) {
			weight[i] = sc.nextInt();
		}
		
		for (int i = 0; i < cnt-1; i++) {
			for (int j = i+1; j < cnt; j++) {
				int sum = weight[i] + weight[j];
				if (sum > limit) {
					continue;
				}
				
				if (sum > maxSum) {
					maxSum = sum;
				}
			}
		}
		
		System.out.printf("#%d %d\n", t+1, maxSum);
	}
}
*/