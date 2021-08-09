// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14yIsqAHYCFAYD

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PWSentence2_1229 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 0; t < 10; t++) {
			int origin = sc.nextInt();
			
			// 원본 암호문 입력
			List<Integer> pwArr = new LinkedList<>();
			for (int i = 0; i < origin; i++) {
				pwArr.add(sc.nextInt());
			}
			
			// 명령어 입력
			int cmdCount = sc.nextInt();
			for (int i = 0; i < cmdCount; i++) {
				switch (sc.next().charAt(0)) {	// 명령어 인식
				case 'I':
				{
					int place = sc.nextInt();	// 넣을 위치
					int count = sc.nextInt();	// 넣을 개수
					
					// 입력받은 순으로 넣기 : 들어갈 index를 한 칸씩 늘려줌
					for (int j = 0; j < count; j++) {
						pwArr.add(place + j, sc.nextInt());
					}
				}
					break;
					
				case 'D':
				{
					int place = sc.nextInt();	// 삭제할 위치
					int count = sc.nextInt();	// 삭제할 개수
					
					// 수를 삭제하면 뒤의 수들이 당겨져오므로 삭제할 index는 그대로
					for (int j = 0; j < count; j++) {
						pwArr.remove(place);
					}
				}
					break;
				}
			}
			
			// 결과 출력
			System.out.printf("#%d ", t+1);
			for (int i = 0; i < 10; i++) {
				System.out.printf("%d ", pwArr.get(i));
			}
			System.out.println();
		}
	}
}