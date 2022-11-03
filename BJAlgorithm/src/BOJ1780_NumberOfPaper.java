// https://www.acmicpc.net/problem/1780

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1780_NumberOfPaper {
	
	static int num1, num2, num3;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		getPaper(0, N-1, 0, N-1);
		System.out.println(num1 + "\n" + num2 + "\n" + num3);
	}
	
	static void getPaper(int yStart, int yEnd, int xStart, int xEnd) {
		int num = map[yStart][xStart];
		boolean isGood = true;
		loop: for (int y = yStart; y <= yEnd; y++) {
			for (int x = xStart; x <= xEnd; x++) {
				if (map[y][x] != num) {
					isGood = false;
					break loop;
				}
			}
		}
		
		if (isGood) {
			switch (num) {
			case -1:
				num1++;
				break;
			case 0:
				num2++;
				break;
			case 1:
				num3++;
				break;
			}
		}
		else {
			int len = yEnd - yStart + 1;
			getPaper(yStart, yStart + len/3 - 1, xStart, xStart + len/3 - 1);
			getPaper(yStart + len/3, yStart + len*2/3 - 1, xStart, xStart + len/3 - 1);
			getPaper(yStart + len*2/3, yStart + len - 1, xStart, xStart + len/3 - 1);
			

			getPaper(yStart, yStart + len/3 - 1, xStart + len/3, xStart + len*2/3 - 1);
			getPaper(yStart + len/3, yStart + len*2/3 - 1, xStart + len/3, xStart + len*2/3 - 1);
			getPaper(yStart + len*2/3, yStart + len - 1, xStart + len/3, xStart + len*2/3 - 1);
			

			getPaper(yStart, yStart + len/3 - 1, xStart + len*2/3, xStart + len - 1);
			getPaper(yStart + len/3, yStart + len*2/3 - 1, xStart + len*2/3, xStart + len - 1);
			getPaper(yStart + len*2/3, yStart + len - 1, xStart + len*2/3, xStart + len - 1);
		}
	}
}