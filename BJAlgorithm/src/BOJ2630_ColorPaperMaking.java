// https://www.acmicpc.net/problem/2630

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630_ColorPaperMaking {
	
	static int N, blue, white;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		blue = 0;
		white = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		divide(1, 1, N, N, N);
		System.out.println(white + "\n" + blue);
	}
	
	static void divide(int y0, int x0, int y1, int x1, int size) {
		boolean isSameColor = true;
		int color = map[y0-1][x0-1];
		
		if (size == 1) {
			if (color == 1)
				blue++;
			else
				white++;
			
			return;
		}
		
		loop: for (int y = y0; y <= y1; y++) {
			for (int x = x0; x <= x1; x++) {
				if (map[y-1][x-1] != color) {
					isSameColor = false;
					break loop;
				}
			}
		}
		
		if (isSameColor) {
			if (color == 1)
				blue++;
			else
				white++;
		}
		else {
			divide(y0, x0, (y0+y1)/2, (x0+x1)/2, size/2);
			divide(y0, (x0+x1)/2+1, (y0+y1)/2, x1, size/2);
			divide((y0+y1)/2+1, x0, y1, (x0+x1)/2, size/2);
			divide((y0+y1)/2+1, (x0+x1)/2+1, y1, x1, size/2);
		}
	}
}