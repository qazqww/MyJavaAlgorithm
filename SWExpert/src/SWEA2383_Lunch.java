// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2383_Lunch {
	
	static boolean[] isFirst;
	static int N, answer, stair1, stair2, pCnt;
	static int[][] map;
	static ArrayList<Place> people, stair;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine()); // 방의 크기
			map = new int[N][N]; // 방의 정보
			people = new ArrayList<>(); // 사람들의 위치
			stair = new ArrayList<>(); // 계단의 위치
			answer = Integer.MAX_VALUE; // 가장 짧게 걸리는 시간
			stair1 = stair2 = 0; // 계단을 내려가는 데에 걸리는 시간
			pCnt = 0; // 사람 수
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) { // 사람이라면 위치와 사람 수를 저장
						people.add(new Place(r, c));
						pCnt++;
					}
					else if (map[r][c] > 1) { // 계단이라면 계단 길이와 위치를 저장
						if (stair1 == 0)
							stair1 = map[r][c];
						else
							stair2 = map[r][c];
						stair.add(new Place(r, c));
					}
				}
			}
			
			isFirst = new boolean[pCnt]; // 1번 계단을 이용하는지 여부 (false면 2번 계단)
			
			sub(0);
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
	
	// 소요 시간을 구하는 코드
	static int func() {
		ArrayList<Integer> dist1 = new ArrayList<>(); // 1번 계단으로 가는 경우 각 사람들과 계단 사이의 거리
		ArrayList<Integer> dist2 = new ArrayList<>();
		for (int i = 0; i < pCnt; i++) {
			if (isFirst[i]) {
				Place p = people.get(i);
				Place s = stair.get(0);
				dist1.add(Math.abs(p.y - s.y) + Math.abs(p.x - s.x)); // 거리를 구해서 넣어준다
			}
			else {
				Place p = people.get(i);
				Place s = stair.get(1);
				dist2.add(Math.abs(p.y - s.y) + Math.abs(p.x - s.x));
			}
		}
		
		int stair1Cnt = 0; // 계단을 건너고 있는 사람 수를 체크 (최대 3명 제한)
		int stair1Time = 0; // 소요 시간을 체크
		int stair1Passed = 0; // 통과한 사람 수
		int stair2Cnt = 0;
		int stair2Time = 0;
		int stair2Passed = 0;
		
		while (stair1Passed < dist1.size()) { // 모두 통과하기까지 반복
			stair1Time++; // 시간 증가
			for (int i = 0; i < dist1.size(); i++) { // 첫 번째 반복 : 이동 처리 + 계단 통과 처리
				dist1.set(i, dist1.get(i)-1); // 한 칸씩 이동
				if (dist1.get(i) == 10000) { // 10000 => 계단을 모두 통과했을 경우
					stair1Cnt--; // 제한 체크에서 1명을 제함
					stair1Passed++; // 통과한 사람으로 처리
				}
			}
			for (int i = 0; i < dist1.size(); i++) { // 두 번째 반복 : 계단 진입 처리
				if (dist1.get(i) < 0 && stair1Cnt < 3) { // 음수인 경우 => 계단에 도착, 3명 제한에 걸리지 않는지 확인
					stair1Cnt++; // 갈 수 있다면 계단 건너는 사람에 추가
					dist1.set(i, 10000 + stair1); // 10000을 더해 계단을 건너는 사람임을 표시
				}
			}
		}
		
		while (stair2Passed < dist2.size()) {
			stair2Time++;
			for (int i = 0; i < dist2.size(); i++) {
				dist2.set(i, dist2.get(i)-1);
				if (dist2.get(i) == 10000) {
					stair2Cnt--;
					stair2Passed++;
				}
			}
			for (int i = 0; i < dist2.size(); i++) {
				if (dist2.get(i) < 0 && stair2Cnt < 3) {
					stair2Cnt++;
					dist2.set(i, 10000 + stair2);
				}
			}
		}
		
		return Math.max(stair1Time, stair2Time);
	}
	
	// 부분집합이 나오는 경우의 수를 완전 탐색하는 코드
	static void sub(int index) {
		
		if (index == pCnt) {
			int result = func();
			answer = Math.min(answer, result);
			return;
		}
		
		isFirst[index] = true;
		sub(index+1);
		isFirst[index] = false;
		sub(index+1);
	}
	
	static class Place {
		int y, x, cnt;

		public Place(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
