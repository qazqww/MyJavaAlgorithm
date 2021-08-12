// https://www.acmicpc.net/problem/2961

import java.util.Scanner;

public class DoyoungFood_2961 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] S = new int[N];
		int[] B = new int[N];
		
		long answer = Long.MAX_VALUE;
		int last = (int)Math.pow(2, N);
		
		for (int i = 0; i < N; i++) {
			S[i] = sc.nextInt();
			B[i] = sc.nextInt();
		}
		
		for (int i = 1; i < last; i++) {		// 1부터 2^N-1까지를 각 재료 포함 여부를 나타내는 부분집합으로 사용 
			long sVal = 1;
			long bVal = 0;
			
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) {		// 각 재료가 들어있는지 비트 자리수로 확인
					sVal *= S[j];	// 신 재료는 곱하고
					bVal += B[j];	// 쓴 재료는 더하고
				}
			}
			
			long temp = Math.abs(sVal - bVal);	// 차이값을 구함
			if (temp < answer) {
				answer = temp;	// 현재 구한 값보다 작으면 답에 넣어둠
			}
			
			if (answer == 0)	// 혹시라도 중간에 가장 작은 값이 나왔으면 종료
				break;
		}
		
		System.out.println(answer);
	}
}