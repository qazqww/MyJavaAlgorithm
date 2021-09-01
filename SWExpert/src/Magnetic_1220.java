// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14hwZqABsCFAYD

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Magnetic_1220 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] map = new char[100][100];
		StringBuilder sb = new StringBuilder();	// 한 세로 라인의 돌 배치
		
		for (int t = 0; t < 10; t++) {
			in.readLine();	// 테이블 크기가 100 고정이므로 주어지는 100은 무시
			int answer = 0;
			
			// 테이블 입력받기
			for (int r = 0; r < 100; r++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int c = 0; c < 100; c++) {
					map[r][c] = st.nextToken().charAt(0);
				}
			}
			
			for (int c = 0; c < 100; c++) {
				sb.setLength(0);
				for (int r = 0; r < 100; r++) {
					if (map[r][c] == '1' || map[r][c] == '2')
						sb.append(map[r][c]);
				}
				String str = sb.toString().replace("12", "Z");	// N극-S극이 붙은 경우를 Z로 변환
				
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == 'Z')		// 총 Z의 개수를 구함
						answer++;
				}
			}
			
			System.out.printf("#%d %d\n", t+1, answer);
		}
	}
}