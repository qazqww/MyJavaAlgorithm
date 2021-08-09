// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14uWl6AF0CFAYD

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PWGenerator_1225 {

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		for (int t = 0; t < 10; t++) {
			int[] nums = new int[8];
			Queue<Integer> queue = new LinkedList<>();
			int min = Integer.MAX_VALUE;
			
			sc.nextInt();
			for (int i = 0; i < 8; i++) {
				nums[i] = sc.nextInt();
				if (nums[i] < min)
					min = nums[i];
				queue.offer(nums[i]);
			}
			
			// 8사이클을 돌면 5바퀴를 돈 후 제자리, 그 동안 모든 수는 15 감소 (-1, -2, -3, -4, -5를 모두 거치므로)
			// 가장 작은 수를 기준으로, 모든 수에서 최대한 15 x n을 뺀다
			int temp = min - (min % 15);
			for (int i = 0; i < 8; i++) {
				nums[i] -= temp;
			}
			
			boolean isEnd = false;	// 0이 나올 경우 종료하기 위한 변수
			while (!isEnd) {
				for (int num = 1; num < 6; num++) {		// 한 사이클
					if (isEnd)
						break;
					
					temp = queue.poll();
					temp -= num;
					
					if (temp <= 0) {
						temp = 0;
						isEnd = true;
					}
					queue.offer(temp);
				}
			}

			System.out.printf("#%d", t+1);
			for (int i = 0; i < 8; i++) {
				System.out.printf(" %d", queue.poll());
			}
			System.out.println();
		}
	}
}
