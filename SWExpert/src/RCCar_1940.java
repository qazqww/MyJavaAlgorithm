// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PjMgaALgDFAUq

import java.util.Scanner;

public class RCCar_1940 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			
			int dist = 0;		// 총 이동 거리
			int speed = 0;		// 현재 속도값
			int cmdCnt = sc.nextInt();
			
			for (int i = 0; i < cmdCnt; i++) {
				switch(sc.nextInt()) {		// 0을 받을 경우 속도가 그대로이므로 switch문은 패스
				case 1:		// 가속 : 입력값만큼 속도를 올림
					speed += sc.nextInt();
					break;
				case 2:		// 감속 : 입력값만큼 속도를 내림
					speed -= sc.nextInt();
					if (speed < 0)	// 속도가 0 밑으로 내려갈 경우, 0으로 적용
						speed = 0;
					break;
				}
				dist += speed;	// 속도값만큼 거리를 이동
			}
			
			System.out.printf("#%d %d\n", t+1, dist);
		}
	}
}