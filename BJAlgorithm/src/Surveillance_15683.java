// https://www.acmicpc.net/problem/15683

import java.util.ArrayList;
import java.util.Scanner;

public class Surveillance_15683 {
	
	static int R, C;
	static int objectSpace;		// 벽이나 CCTV가 있는 칸의 수
	static int mostCovered;		// CCTV로 커버할 수 있는 가장 큰 칸 개수
	static int[][] map;
	static int[][] copyMap;		// CCTV 조합을 시험해보기 위해 복사한 맵
	static ArrayList<CCTV> cctv;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new int[R][C];
		copyMap = new int[R][C];
		cctv = new ArrayList<>();
		objectSpace = 0;
		mostCovered = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[r][c] = sc.nextInt();
				if (map[r][c] > 0) {	// 빈 공간(0)이 아니면 오브젝트 개수에 포함
					objectSpace++;
				}
				if (map[r][c] != 0 && map[r][c] != 6)	// 1~5라면 CCTV 리스트에 위치와 감시 방법 추가
					cctv.add(new CCTV(r, c, map[r][c]));
			}
		}
		
		setCCTV(0);
		System.out.println(R * C - (objectSpace + mostCovered));	// 전체 범위에서 오브젝트 개수와 최대 감시 칸 개수를 뺌
	}
	
	// cctv 배치 조합(중복순열?)을 생성
	static void setCCTV(int index) {
		if (index == cctv.size()) {
			for (int r = 0; r < R; r++)
				System.arraycopy(map[r], 0, copyMap[r], 0, C);
			
			mostCovered = Math.max(mostCovered, checkRange());
			return;
		}
		
		// 현재 인덱스의 cctv에 감시 방법을 설정해둔 뒤, 재귀를 통해 다음 인덱스 cctv를 설정
		
		// 상:8 하:4 좌:2 우:1
		switch (cctv.get(index).model) {
		case 1:		// 1방향 감시
			cctv.get(index).range = 1;	// 우
			setCCTV(index+1);
			cctv.get(index).range = 2;	// 좌
			setCCTV(index+1);
			cctv.get(index).range = 4;	// 하
			setCCTV(index+1);
			cctv.get(index).range = 8;	// 상
			setCCTV(index+1);
			break;
		case 2:		// 2방향(반대 방향) 감시
			cctv.get(index).range = 3;	// 좌2 + 우1
			setCCTV(index+1);
			cctv.get(index).range = 12;	// 상8 + 하4
			setCCTV(index+1);
			break;
		case 3:		// 2방향(직각 방향) 감시
			cctv.get(index).range = 5;	// 우1 + 하4
			setCCTV(index+1);
			cctv.get(index).range = 6;	// 좌2 + 하4
			setCCTV(index+1);
			cctv.get(index).range = 9;	// 우1 + 상8
			setCCTV(index+1);
			cctv.get(index).range = 10;	// 좌2 + 상8
			setCCTV(index+1);
			break;
		case 4:		// 3방향 감시
			cctv.get(index).range = 7;	// 상 제외
			setCCTV(index+1);
			cctv.get(index).range = 11;	// 하 제외
			setCCTV(index+1);
			cctv.get(index).range = 13;	// 좌 제외
			setCCTV(index+1);
			cctv.get(index).range = 14;	// 우 제외
			setCCTV(index+1);
			break;
		case 5:		// 4방향 감시
			cctv.get(index).range = 15;	// 상하좌우
			setCCTV(index+1);
			break;
		}
	}
	
	// 만들어진 cctv 조합으로 맵 복사본에 적용해보기
	static int checkRange() {
		int covered = 0;	// cctv로 커버할 수 있는 칸 수
		for (int cc = 0; cc < cctv.size(); cc++) {
			CCTV tv = cctv.get(cc);
			if ((tv.range & (1 << 0)) > 0) {			// 우측에 해당하는 1 과 &연산
				for (int c = tv.c + 1; c < C; c++) { 	// 우측으로 한 칸씩 체크
					if (copyMap[tv.r][c] == 6)		// 벽이 존재하면 정지
						break;
					if (copyMap[tv.r][c] == 0) {	// 빈 칸일 경우 감시가능한 칸 수에 더함
						copyMap[tv.r][c] = 9;		// 다른 cctv에 계산되지 않도록 처리
						covered++;
					}
				}
			}
			if ((tv.range & (1 << 1)) > 0) {	// 좌측
				for (int c = tv.c - 1; c >= 0; c--) { 
					if (copyMap[tv.r][c] == 6)
						break;
					if (copyMap[tv.r][c] == 0) {
						copyMap[tv.r][c] = 9;
						covered++;
					}
				}
			}
			if ((tv.range & (1 << 2)) > 0) {	// 하측
				for (int r = tv.r + 1; r < R; r++) { 
					if (copyMap[r][tv.c] == 6)
						break;
					if (copyMap[r][tv.c] == 0) {
						copyMap[r][tv.c] = 9;
						covered++;
					}
				}
			}
			if ((tv.range & (1 << 3)) > 0) {	// 상측
				for (int r = tv.r - 1; r >= 0; r--) { 
					if (copyMap[r][tv.c] == 6)
						break;
					if (copyMap[r][tv.c] == 0) {
						copyMap[r][tv.c] = 9;
						covered++;
					}
				}
			}
		}
		return covered;
	}
}

class CCTV {
	int r, c;
	int model;
	int range;	// cctv 커버 방향을 비트마스킹 체크하여 담음
	
	public CCTV(int r, int c, int model) {
		this.r = r;
		this.c = c;
		this.model = model;
	}
}