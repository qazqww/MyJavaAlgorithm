// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14w-rKAHACFAYD

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PWSentence1_1228 {

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
				switch (sc.next().charAt(0)) {		// 명령어 인식
				case 'I':
					int place = sc.nextInt();	// 넣을 위치
					int count = sc.nextInt();	// 넣을 개수
					
					// 입력받은 순으로 넣기 : 들어갈 index를 한 칸씩 늘려줌
					for (int j = 0; j < count; j++) {
						pwArr.add(place + j, sc.nextInt());
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

/* 메인 로직에 스택을 사용해본 코드
case 'I':
    int place = sc.nextInt();
    int count = sc.nextInt();
    Stack<Integer> stack = new Stack<>();
     
    for (int j = 0; j < count; j++) {
        stack.push(sc.nextInt());
    }
    while (!stack.isEmpty()) {
        pwArr.add(place, stack.pop());
    }
     
    break;
*/