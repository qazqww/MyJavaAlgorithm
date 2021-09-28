// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWzaq5KKk_ADFAVU

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA8458_ToZeroPoint {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st;
			int N = Integer.parseInt(in.readLine());
			int[] dots = new int[N];	// 점들의 정보를 담음

			int maxNum = -1;	// 가장 멀리있는 점과의 거리
			int moveNum = 0;	// 마지막으로 이동한 거리

			// 입력 부분
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				dots[i] = Math.abs(Integer.parseInt(st.nextToken()))	// 원점과 점 사이의 맨해튼 거리를 저장
						+ Math.abs(Integer.parseInt(st.nextToken()));
				
				if (dots[i] > maxNum)	// 가장 먼 점과의 거리 갱신
					maxNum = dots[i];
			}
			
			// 짝수 혹은 홀수만으로 이루어져있는지 검사
			boolean canMove = true;
			boolean[] isEven = new boolean[N];
			for (int i = 0; i < dots.length; i++) {
				isEven[i] = (dots[i] % 2 == 0) ? true : false;	// 짝수인지 여부를 저장
				if (isEven[0] != isEven[i])	// 첫 인덱스와 비교하여 값이 다르면 정답이 없다고 판단
				{
					canMove = false;
					break;
				}
			}

			if (!canMove)	// 짝수 홀수가 섞여있는 경우
				moveNum = -1;
			else if (maxNum == 0)	// 가장 먼 점이 원점일 경웅
				moveNum = 0;
			else {
				while (true) {	// 일반적인 경우
					moveNum++;
					
					// 짝홀수 여부가 같고 이동거리가 현재 위치보다 크면 마지막 이동이 된다
					if (moveNum >= maxNum && (moveNum - maxNum) % 2 == 0)
						break;
						
					maxNum -= moveNum;
				}
			}

			sb.append("#" + (t+1) + " " + moveNum);
			System.out.println(sb);
		}
	}
}
