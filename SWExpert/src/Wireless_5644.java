// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Wireless_5644 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			
			int[][] map = new int[10][10];	// 충전기 배치 정보를 담을 맵
			int answer = 0;		// 구해야 할 총 충전량
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			int moveTime = Integer.parseInt(st.nextToken()) + 1;	// 초기 위치(0초)를 포함한 총 이동 시간
			int BCcnt = Integer.parseInt(st.nextToken());			// 충전기 개수
			
			// 사용자 이동 정보 입력
			int[] moveA = new int[moveTime];
			int[] moveB = new int[moveTime];
			
			// 초기 위치(0초) 포함
			moveA[0] = 0;
			moveB[0] = 0;
			
			// 사용자 A의 움직임 입력받기
			st = new StringTokenizer(in.readLine());
			for (int i = 1; i < moveTime; i++)
				moveA[i] = Integer.parseInt(st.nextToken());
			
			// 사용자 B의 움직임 입력받기
			st = new StringTokenizer(in.readLine());
			for (int i = 1; i < moveTime; i++)
				moveB[i] = Integer.parseInt(st.nextToken());
			
			ArrayList<Battery> bc = new ArrayList<>();		// 충전기 정보를 담을 리스트
			int[] bcPower = new int[BCcnt];					// 번호 index에 성능을 담는 충전기 배열
			
			// 충전기 정보 입력
			for (int num = 0; num < BCcnt; num++) {
				st = new StringTokenizer(in.readLine());
				int bcX = Integer.parseInt(st.nextToken()) - 1;		// map에서의 X index
				int bcY = Integer.parseInt(st.nextToken()) - 1;		// map에서의 Y index
				int range = Integer.parseInt(st.nextToken());		// 충전기의 충전 범위
				bcPower[num] = Integer.parseInt(st.nextToken());
				bc.add(new Battery(num, bcPower[num]));
				
				// (bcY - range) ~ (bcY + range)
				// 충전기를 맵에 적용
				for (int y = bcY - range; y <= bcY + range; y++) {
					if (y < 0) continue;
					if (y >= 10) break;
					for (int x = bcX - range; x <= bcX + range; x++) {
						if (x < 0) continue;
						if (x >= 10) break;
						
						if (Math.abs(bcX - x) + Math.abs(bcY - y) <= range) {
							map[y][x] += (1 << num);
						}
					}
				}
			}
			
			Collections.sort(bc, (bc1, bc2) -> bc2.power-bc1.power);
			
			int ay = 0, ax = 0;
			int by = 9, bx = 9;
			for (int sec = 0; sec < moveTime; sec++) {
				// 이동 처리
				switch(moveA[sec]) {
				case 1: { ay -= 1; break; }
				case 2: { ax += 1; break; }
				case 3: { ay += 1; break; }
				case 4: { ax -= 1; break; }
				}
				switch(moveB[sec]) {
				case 1: { by -= 1; break; }
				case 2: { bx += 1; break; }
				case 3: { by += 1; break; }
				case 4: { bx -= 1; break; }
				}
				
				// 현재 위치에 어떤 배터리가 있는지 체크
				// 배터리 파워가 높은 순으로 A에서 2개, B에서 2개까지 취함
				ArrayList<Integer> aBattery = new ArrayList<>();
				ArrayList<Integer> bBattery = new ArrayList<>();
				for (Battery temp : bc) {
					int temp2 = map[ay][ax];
					if ((temp2 &= (1 << temp.num)) >= 1) {
						aBattery.add(temp.num);
						if (aBattery.size() == 2)
							break;
					}
				}
				for (Battery temp : bc) {
					int temp2 = map[by][bx];
					if ((temp2 &= (1 << temp.num)) >= 1) {
						bBattery.add(temp.num);
						if (bBattery.size() == 2)
							break;
					}
				}
				
				// 경우의 수를 다 따져보며 충전될 배터리 양 계산
				if (aBattery.size() != 0 && bBattery.size() == 0)	// a만 배터리 영역에 존재
					answer += bcPower[aBattery.get(0)];
				else if (aBattery.size() == 0 && bBattery.size() != 0)	// b만 배터리 영역에 존재
					answer += bcPower[bBattery.get(0)];
				else if (aBattery.size() != 0 && bBattery.size() != 0) {	// 둘 다 배터리 영역에 존재
					
					// A의 최고 파워 배터리와 B의 최고 파워 배터리가 겹치면
					if (aBattery.get(0) == bBattery.get(0)) {
						if (aBattery.size() == 1 && bBattery.size() == 1)	// 공유해야하는 경우
							answer += bcPower[aBattery.get(0)];
						else if (aBattery.size() == 2 && bBattery.size() == 1) {
							answer += bcPower[aBattery.get(0)];
							answer += bcPower[aBattery.get(1)];
						}
						else if (aBattery.size() == 1 && bBattery.size() == 2) {
							answer += bcPower[aBattery.get(0)];
							answer += bcPower[bBattery.get(1)];
						}
						else {
							answer += bcPower[aBattery.get(0)];
							answer += (bcPower[aBattery.get(1)] > bcPower[bBattery.get(1)])
									? bcPower[aBattery.get(1)] : bcPower[bBattery.get(1)];
						}
					}
					else {
						answer += bcPower[aBattery.get(0)];
						answer += bcPower[bBattery.get(0)];
					}
				}
			}
			
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
}
	
class Battery {
	int num;
	int power;
	
	public Battery(int num, int power) {
		this.num = num;
		this.power = power;
	}
}