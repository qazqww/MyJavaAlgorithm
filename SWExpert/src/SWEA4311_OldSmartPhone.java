// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWL2vlPKMlQDFAUE

import java.util.Scanner;

public class SWEA4311_OldSmartPhone {
	
	static int[] numInfo;
	static boolean[] numList;
	static boolean[] op;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int Op = sc.nextInt();
			int Max = sc.nextInt();
			
			numList = new boolean[10]; // 가능한 숫자
			op = new boolean[4]; // 가능한 연산자
			
			for (int i = 0; i < N; i++)
				numList[sc.nextInt()] = true;
			
			for (int i = 0; i < Op; i++)
				op[sc.nextInt() - 1] = true;
			
			int target = sc.nextInt();
			
			numInfo = new int[1000]; // 각 숫자별로 필요한 터치 수
			for (int i = 0; i < 1000; i++)
				numInfo[i] = 100;
            
            if (numList[0]) // 0은 따로 처리
				numInfo[0] = 1;
			
            // 중복 순열을 통해 숫자 터치만으로 만들 수 있는 수 정리
			for (int i = 0; i < 10; i++) {
				if (numList[i])
					permu(1, i);
			}
			
			boolean newNum = false;
			if (numInfo[target] > Max) { // 중복 순열로 타겟 넘버가 나오지 않았다면
				while (true) { // 연산으로 새로운 수를 만들 수 없을 때까지 반복
					newNum = false;
					for (int i = 0; i < 1000; i++) {
						if (numInfo[i] >= Max - 1) continue;
						for (int j = 0; j < 1000; j++) {
							if (numInfo[j] >= Max - 1) continue;
							
							// = 연산자의 포함 여부 확인
							int opSpace = 1;
							if (numInfo[i] <= 3 && numInfo[j] <= 3)
								opSpace = 2;
							
							// 각 연산자를 시도하여 더 적은 수로 연산가능하면 갱신
							if (op[0] && numInfo[j] <= 3 && i+j < 1000 && numInfo[i + j] > numInfo[i] + numInfo[j] + opSpace) {
								numInfo[i + j] = numInfo[i] + numInfo[j] + opSpace;
								newNum = true;
							}
							if (op[2] && numInfo[j] <= 3 && i*j < 1000 && numInfo[i * j] > numInfo[i] + numInfo[j] + opSpace) {
								numInfo[i * j] = numInfo[i] + numInfo[j] + opSpace;
								newNum = true;
							}
							
							if (op[1] && numInfo[j] <= 3 && i-j >= 0 && numInfo[i - j] > numInfo[i] + numInfo[j] + opSpace) {
								numInfo[i - j] = numInfo[i] + numInfo[j] + opSpace;
								newNum = true;
							}
							if (op[1] && numInfo[i] <= 3 && j-i >= 0 && numInfo[j - i] > numInfo[i] + numInfo[j] + opSpace) {
								numInfo[j - i] = numInfo[i] + numInfo[j] + opSpace;
								newNum = true;
							}
							
							if (op[3] && numInfo[j] <= 3 && j != 0 && numInfo[i / j] > numInfo[i] + numInfo[j] + opSpace) {
								numInfo[i / j] = numInfo[i] + numInfo[j] + opSpace;
								newNum = true;
							}
							if (op[3] && numInfo[i] <= 3 && i != 0 && numInfo[j / i] > numInfo[i] + numInfo[j] + opSpace) {
								numInfo[j / i] = numInfo[i] + numInfo[j] + opSpace;
								newNum = true;
							}
						}
					}
					
					if (!newNum)
						break;
				}
			}
			
			System.out.printf("#%d %d\n", t+1, numInfo[target] > Max ? -1 : numInfo[target]);
		}
	}
	
	static void permu(int cnt, int num) {
		if (num > 999 || num == 0)
			return;
		
		numInfo[num] = cnt;
		
		for (int i = 0; i < 10; i++) {
			if (numList[i]) {
				int temp = num * 10 + i;
				permu(cnt + 1, temp);
			}
		}
	}
}