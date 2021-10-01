// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4013_UnusualMagnet {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int[][] magnets = new int[4][8];	// 자석의 정보를 담음
			int[] turnDir = new int[4];			// 자석별로 회전할 방향을 담음
			boolean[] connect = new boolean[3];	// 자석이 함께 회전하는지를 담음 (1-2, 2-3, 3-4 자석의 연결 여부)
			
			// 자석 정보 입력
			int turn = Integer.parseInt(in.readLine());
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 회전
			for (int i = 0; i < turn; i++) {
				st = new StringTokenizer(in.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				turnDir = new int[4];
				connect = new boolean[3];

				// j번째 자석의 오른쪽과 j+1번째 자석의 왼쪽을 비교하여 자석이 함께 도는지 확인
				for (int j = 0; j < 3; j++) {
					if (magnets[j][2] != magnets[j+1][6])
						connect[j] = true;
				}
				
				turnDir[num] = dir;
				// 옆 자석이 함께 돌 수 있는지 확인하고, 함께 돈다면 돌 방향을 정해줌
				if (num == 0) {		// 첫 자석이 회전할 경우 2->3->4번째 자석 순으로 연결 여부 확인
					if (connect[0]) {
						turnDir[1] = -dir;
						if (connect[1]) {
							turnDir[2] = dir;
							if (connect[2])
								turnDir[3] = -dir;
						}
					}
				}
				else if (num == 1) {	// 두번째 자석이 회전할 경우 1,3 -> 4번째 자석 순으로 연결 여부 확인
					if (connect[0]) {
						turnDir[0] = -dir;
					}
					if (connect[1]) {
						turnDir[2] = -dir;
						if (connect[2])
							turnDir[3] = dir;
					}
				}
				else if (num == 2) {
					if (connect[1]) {
						turnDir[1] = -dir;
						if (connect[0])
							turnDir[0] = dir;
					}
					if (connect[2]) {
						turnDir[3] = -dir;
					}
				}
				else if (num == 3) {
					if (connect[2]) {
						turnDir[2] = -dir;
						if (connect[1]) {
							turnDir[1] = dir;
							if (connect[0])
								turnDir[0] = -dir;
						}
					}
				}
				
				// turnDir에 따라 자석을 회전
				for (int j = 0; j < 4; j++) {
					if (turnDir[j] == -1) {
						int temp = magnets[j][0];
						for (int k = 0; k < 7; k++) {
							magnets[j][k] = magnets[j][k+1];
						}
						magnets[j][7] = temp;
					}
					else if (turnDir[j] == 1) {
						int temp = magnets[j][7];
						for (int k = 7; k > 0; k--) {
							magnets[j][k] = magnets[j][k-1];
						}
						magnets[j][0] = temp;
					}
				}
			}
			
			// 몇 점을 획득했는지 계산
			int answer = 0;
			for (int i = 0; i < 4; i++) {
				if (magnets[i][0] == 1)
					answer += Math.pow(2, i);
			}
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
}