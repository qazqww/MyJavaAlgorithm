// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class crop_2805 {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 0; t < T; t++) {
			int value = 0;
			
			int N = sc.nextInt();
			sc.nextLine();
			int half = (N-1)/2;
			
			// 값을 받아오면서 수확 범위에 해당하는지 체크하여 바로 결과값에 더해주는 방식
			// 즉, 각 행마다 중앙으로부터 특정 범위만큼의 값만 취하여 더해줌
			// y가 0일 때 중앙+-0칸, 1일 때 중앙+-1칸...	=> half - |half - y|로 수확 범위를 구함
			
			for (int y = 0; y < N; y++) {
				char[] temp = sc.nextLine().toCharArray();	// 숫자를 1자리씩 받기 위해 char로 받아 int로 parse하는 방법을 사용
				for (int x = 0; x < N; x++) {
					int dist = half - Math.abs(half - y);
					if (x >= half - dist && x <= half + dist) {
						value += temp[x] - 48;
					}
				}
			}
			
			System.out.printf("#%d %d\n", t+1, value);
		}
	}
}
