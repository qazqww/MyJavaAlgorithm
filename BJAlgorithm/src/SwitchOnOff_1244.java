// https://www.acmicpc.net/problem/1244

import java.util.Scanner;

public class SwitchOnOff_1244 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sNum = sc.nextInt();			// 스위치 개수
		int[] arr = new int[sNum];			// 스위치 배치
		for (int i = 0; i < sNum; i++) {
			arr[i] = sc.nextInt();
		}
		int pNum = sc.nextInt();			// 사람 개수
		for (int i = 0; i < pNum; i++) {
			int gender = sc.nextInt();		// 성별
			int num = sc.nextInt();			// 배정받은 숫자
			
			switch (gender) {
			
			// 남자인 경우 -> 배수 스위치 조작
			case 1:
				for (int j = 0; j < sNum; j++) {
					if ((j + 1) % num == 0) {
						arr[j] = 1 - arr[j];
					}
				}
				break;
			
			// 여자인 경우 -> 현 위치 기준 가장 넓은 대칭을 가진 범위의 스위치 조작
			case 2:
				int offset = 1;
				num--;
				arr[num] = 1 - arr[num];								// 먼저 자신의 스위치를 조작하고
				
				while (num - offset >= 0 && num + offset < sNum
						&& arr[num - offset] == arr[num + offset]) {	// 양 옆의 수가 대칭인지(같은지) 비교
					
					arr[num - offset] = 1 - arr[num - offset];
					arr[num + offset] = 1 - arr[num + offset];
					offset++;
				}
				break;
			}
		}
		
		for (int i = 0; i < sNum; i++) {
			System.out.printf("%d ", arr[i]);
			if ((i + 1) % 20 == 0) {
				System.out.println();
			}
		}
	}
}
