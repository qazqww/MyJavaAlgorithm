// https://www.acmicpc.net/problem/9663

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9663_NQueen {
	
	static int answer = 0;
	static int N;
	static int[] place;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		place = new int[N];
		Arrays.fill(place, -1);
		
		recursive(0);
		System.out.println(answer);
	}
	
	static void recursive(int depth) {
		if (depth >= N) {
			answer++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (valid(depth, i)) {
				place[depth] = i;
				recursive(depth + 1);
				place[depth] = -1;
			}
		}
	}
	
	static boolean valid(int y, int x) {
		boolean isValid = true;
		
		for (int i = 0; i < y; i++) {
			if (place[i] == x || y - i == Math.abs(x - place[i]))
				isValid = false;
			
			if (!isValid)
				break;
		}
		
		return isValid;
	}
}
