// https://www.acmicpc.net/problem/16926

import java.util.Scanner;

public class ArraySpin1_16926 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int yLen = sc.nextInt();
		int xLen = sc.nextInt();
		int cnt = sc.nextInt();
		int[][] arr = new int[yLen][xLen];
		
		// 입력
		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				arr[y][x] = sc.nextInt();
			}
		}
		
		for (int j = 0; j < cnt; j++) {
			
			// 회전 부분
			for (int i = 0; i < Math.min(xLen, yLen) / 2; i++) {	// i -> 바깥쪽에서 안쪽까지 순서대로 처리
				int x = i;
				int y = i;
				int temp = arr[i][i];	// 시작점은 temp로 받아뒀다가 마지막 칸에 넣어줌
				
				for (; x < xLen - i - 1; x++) {		// 윗쪽 줄 처리
					arr[y][x] = arr[y][x+1];
				}
				for (; y < yLen - i - 1; y++) {		// 오른쪽 줄 처리
					arr[y][x] = arr[y+1][x];
				}
				for (; x > i; x--) {				// 밑쪽 줄 처리
					arr[y][x] = arr[y][x-1];
				}
				for (; y > i; y--) {				// 왼쪽 줄 처리
					arr[y][x] = arr[y-1][x];
				}
				arr[y+1][x] = temp;
			}
		}
		
		// 출력
		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				System.out.print(arr[y][x] + " ");
			}
			System.out.println();
		}
	}
}