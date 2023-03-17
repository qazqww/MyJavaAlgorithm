// https://www.acmicpc.net/problem/2470

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470_TwoSolution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int min = Integer.MAX_VALUE;
		int front = -1;
		int back = -1;
		for (int f = 0, b = N - 1; f < b; ) {
			int result = arr[f] + arr[b];
			if (min > Math.abs(result)) {
				min = Math.abs(result);
				front = f;
				back = b;
			}

			if (result >= 0) {
				b--;
			}
			else {
				f++;
			}
		}

		System.out.println(arr[front] + " " + arr[back]);
	}
}