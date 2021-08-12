// https://www.acmicpc.net/problem/16935

import java.util.ArrayList;
import java.util.Scanner;

public class ArraySpin3_16935 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int yLen = sc.nextInt();
		int xLen = sc.nextInt();
		int opLen = sc.nextInt();

		ArrayList<Integer> ops = new ArrayList<>();		// 수행할 연산을 담음
		int[][] arr = new int[yLen][xLen];
		
		// 배열 입력
		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				arr[y][x] = sc.nextInt();
			}
		}

		// 연산 입력
		for (int op = 0; op < opLen; op++) {
			ops.add(sc.nextInt());
		}

		// 연산 수행
		for (int op : ops) {
			switch (op) {
			
			case 1:			// 상하 반전 : 맨 윗줄부터 맨 아랫줄과 swap
				for (int y = 0; y < yLen/2; y++) {
					int[] temp = arr[y];
					arr[y] = arr[yLen - y - 1];
					arr[yLen - y - 1] = temp;
				}
				break;
				
			case 2:			// 좌우 반전 : 행별로 reverse하여 덮어씌우기
				for (int y = 0; y < yLen; y++) {
					int[] temp = new int[xLen];
					for (int x = 0; x < xLen; x++) {
						temp[x] = arr[y][xLen - x - 1];
					}
					arr[y] = temp;
				}
				break;
				
			case 3:			// 오른쪽 90도 회전 : 새로운 배열을 만들어 대체
			{
				int[][] newArr = new int[xLen][yLen];
				for (int y = 0; y < xLen; y++) {
					for (int x = 0; x < yLen; x++) {
						newArr[y][x] = arr[yLen - x - 1][y];
					}
				}
				arr = new int[xLen][yLen];
				System.arraycopy(newArr, 0, arr, 0, newArr.length);
				
				int temp = xLen;
				xLen = yLen;
				yLen = temp;
			}
				break;
				
			case 4:			// 왼쪽 90도 회전
			{
				int[][] newArr = new int[xLen][yLen];
				for (int y = 0; y < xLen; y++) {
					for (int x = 0; x < yLen; x++) {
						newArr[y][x] = arr[x][xLen - y - 1];
					}
				}
				arr = new int[xLen][yLen];
				System.arraycopy(newArr, 0, arr, 0, newArr.length);
				
				int temp = xLen;
				xLen = yLen;
				yLen = temp;
			}
				break;
				
			case 5:			// 구역 시계 방향 이동 : 새로운 배열을 생성하고, 인덱스에서 반(half)만큼 빼거나 더하여 원하는 구역의 원소를 가져옴
			{
				int[][] newArr = new int[yLen][xLen];
				for (int y = 0; y < yLen; y++) {
					for (int x = 0; x < xLen; x++) {						
						if (x < xLen/2 && y < yLen/2) {			// 1번 구역 -> 4번 입력
							newArr[y][x] = arr[y + yLen/2][x];
						}
						else if (x >= xLen/2 && y < yLen/2) {	// 2번 구역 -> 1번 입력
							newArr[y][x] = arr[y][x - xLen/2];
						}
						else if (x >= xLen/2 && y >= yLen/2) {	// 3번 구역 -> 2번 입력
							newArr[y][x] = arr[y - yLen/2][x];
						}
						else if (x < xLen/2 && y >= yLen/2) {	// 4번 구역 -> 3번 입력
							newArr[y][x] = arr[y][x + xLen/2];
						}
					}
				}
				arr = newArr;
			}
				break;
				
			case 6:			// 구역 반시계 방향 이동
			{
				int[][] newArr = new int[yLen][xLen];
				for (int y = 0; y < yLen; y++) {
					for (int x = 0; x < xLen; x++) {						
						if (x < xLen/2 && y < yLen/2) {			// 1번 구역 -> 2번 입력
							newArr[y][x] = arr[y][x + xLen/2];
						}
						else if (x >= xLen/2 && y < yLen/2) {	// 2번 구역 -> 3번 입력
							newArr[y][x] = arr[y + yLen/2][x];
						}
						else if (x >= xLen/2 && y >= yLen/2) {	// 3번 구역 -> 4번 입력
							newArr[y][x] = arr[y][x - xLen/2];
						}
						else if (x < xLen/2 && y >= yLen/2) {	// 4번 구역 -> 1번 입력
							newArr[y][x] = arr[y - yLen/2][x];
						}
					}
				}
				arr = newArr;
			}
				break;
			}
		}

		for (int y = 0; y < yLen; y++) {
			for (int x = 0; x < xLen; x++) {
				System.out.print(arr[y][x] + " ");
			}
			System.out.println();
		}
	}
}