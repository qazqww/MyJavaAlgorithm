// https://www.acmicpc.net/problem/12015

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12015_LIS2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}
		
		int[] LIS = new int[N];
		int size = 0;
		for (int i = 0; i < N; i++) {
			int order = Arrays.binarySearch(LIS, 0, size, arr[i]);
			if (order < 0) {
				int idx = Math.abs(order) - 1;
				LIS[idx] = arr[i];
				
				if (idx == size)
					size++;
			}
		}

		System.out.println(size);
		for (int i = 0; i < size; i++) {
			System.out.print(LIS[i] + " ");
		}
	}
}