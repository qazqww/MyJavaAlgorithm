// https://www.acmicpc.net/problem/17406

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ArraySpin4_17406 {

	static int cnt = 0; 	// 회전 연산 개수
	static List<int[]> permu = new ArrayList<>();	// 순열들을 담는 리스트
	static int[] temp;		// 순열을 뽑아낼 때 사용할 임시 배열
	static boolean[] used;

	static void getPermu(int index) {
		if (index == cnt) {
			permu.add(Arrays.copyOf(temp, temp.length));
			return;
		}

		for (int i = 0; i < cnt; i++) {
			if (used[i])
				continue;
			used[i] = true;
			temp[index] = i;
			getPermu(index + 1);
			used[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int yLen = Integer.parseInt(st.nextToken());
		int xLen = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		int[][] arr = new int[yLen][xLen];
		int[][] copyArr = new int[yLen][xLen];

		temp = new int[cnt];
		used = new boolean[cnt];
		int[] opY = new int[cnt];
		int[] opX = new int[cnt];
		int[] opR = new int[cnt];
		int min = Integer.MAX_VALUE;

		getPermu(0);

		// 배열 입력
		for (int y = 0; y < yLen; y++) {
			st = new StringTokenizer(in.readLine());
			for (int x = 0; x < xLen; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		// 회전 연산 입력
		for (int j = 0; j < cnt; j++) {
			st = new StringTokenizer(in.readLine());
			opY[j] = Integer.parseInt(st.nextToken()) - 1;
			opX[j] = Integer.parseInt(st.nextToken()) - 1;
			opR[j] = Integer.parseInt(st.nextToken());
		}

		// 순열에서 뽑아내기
		for (int p = 0; p < permu.size(); p++) {
			for (int n = 0; n < arr.length; n++) {
				System.arraycopy(arr[n], 0, copyArr[n], 0, arr[0].length);
			}
			// 순열의 순서대로 수행하기
			for (int pOrder = 0; pOrder < permu.get(p).length; pOrder++) {
				int now = permu.get(p)[pOrder];
				int yCen = opY[now];
				int xCen = opX[now];
				int range = opR[now];

				for (int i = 1; i <= range; i++) {
					int x = xCen - i;
					int y = yCen - i;
					int temp = copyArr[y][x]; // 시작점은 temp로 받아뒀다가 마지막 칸에 넣어줌

					for (; y < yCen + i; y++) { // 왼쪽 줄 처리
						copyArr[y][x] = copyArr[y + 1][x];
					}
					for (; x < xCen + i; x++) { // 밑쪽 줄 처리
						copyArr[y][x] = copyArr[y][x + 1];
					}
					for (; y > yCen - i; y--) { // 오른쪽 줄 처리
						copyArr[y][x] = copyArr[y - 1][x];
					}
					for (; x > xCen - i; x--) { // 윗쪽 줄 처리
						copyArr[y][x] = copyArr[y][x - 1];
					}

					copyArr[y][x + 1] = temp;
				}
			}

			for (int y = 0; y < yLen; y++) {
				int sum = 0;
				for (int x = 0; x < xLen; x++) {
					sum += copyArr[y][x];
				}
				if (sum < min)
					min = sum;
			}
		}

		System.out.print(min);
	}
}
