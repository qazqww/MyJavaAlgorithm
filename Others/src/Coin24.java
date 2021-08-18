// 24개의 동전 중 가짜 동전 하나를 구하라.

import java.util.Scanner;

public class Coin24 {
	
	static final int COIN_NUM = 24;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] weight = new int[COIN_NUM];
		for (int i = 0; i < COIN_NUM; i++) {
			weight[i] = sc.nextInt();
		}

		int start = 0;
		int end = weight.length;
		while (start <= end) {
			int half = (start + end) / 2;
			System.out.println(start + " " + half + " " + end);
			
			int front = 0;
			int rear = 0;
			
			if ((end - start) % 2 == 1) {
				for (int i = start; i < half; i++) {
					front += weight[i];
				}
				for (int i = half + 1; i < end; i++) {
					rear += weight[i];
				}
				
				System.out.println(front + " " + rear);
				
				if (front == 0) {
					System.out.println(start + 1);
					break;
				} else if (rear == 0) {
					System.out.println(end);
					break;
				} else {
					System.out.println(half + 1);
					break;
				}
			}
			else {
				for (int i = start; i < half; i++) {
					front += weight[i];
				}
				for (int i = half; i < end; i++) {
					rear += weight[i];
				}
				System.out.println(front + " " + rear);
				
				if (front == 0) {
					System.out.println(start + 1);
					break;
				} else if (rear == 0) {
					System.out.println(end);
					break;
				}
			}
			
			if (front < rear) {
				end = half;
			} else {
				start = half;
			}
		}

	}
}