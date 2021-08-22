// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OptimalPath_1247 {
	
	static int N;
	static int[][] place;
	static int[] first, last;
	static int[] order;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine());
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			// 회사 좌표 입력
			first = new int[2];
			first[0] = Integer.parseInt(st.nextToken());
			first[1] = Integer.parseInt(st.nextToken());
			
			// 집 좌표 입력
			last = new int[2];
			last[0] = Integer.parseInt(st.nextToken());
			last[1] = Integer.parseInt(st.nextToken());
			
			answer = Integer.MAX_VALUE;
			place = new int[N][3];		// 고객 정보 { x, y, 방문여부 }
			order = new int[N];			// 방문 순서
			
			// 고객 좌표 입력
			for (int n = 0; n < N; n++) {
				place[n][0] = Integer.parseInt(st.nextToken());
				place[n][1] = Integer.parseInt(st.nextToken());
			}
			
			permu(0, 0);
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
	
	// 순열을 사용하여 방문 순서 완전 탐색으로 뽑아내기
	static public void permu(int index, int sum) {
		
		if (sum > answer)	// 이미 구했던 이동 거리값보다 길어졌을 경우 가지치기
			return;
		
		if (index == N) {
			// 집까지 돌아가는 거리 계산
			sum += Math.abs(last[0] - place[order[index-1]][0]) + Math.abs(last[1] - place[order[index-1]][1]);
			if (sum < answer)
				answer = sum;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (place[i][2] == 0) {
				place[i][2] = 1;	// 방문 표시
				order[index] = i;	// i번째 place를 방문 리스트에 추가
				
				if (index == 0) {	// 첫 방문일 경우 회사와의 거리를 더함
					permu(index+1, sum + Math.abs(first[0] - place[i][0]) + Math.abs(first[1] - place[i][1]));
				} else {			// 나머지 경우 좌표 간의 이동 거리를 더함
					permu(index+1, sum + Math.abs(place[i][0] - place[order[index-1]][0])
							+ Math.abs(place[i][1] - place[order[index-1]][1]));
				}
				
				place[i][2] = 0;	// 방문 표시 지우기
			}
		}
	}
}