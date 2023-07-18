package B5toS4;// https://www.acmicpc.net/problem/1059

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1059_GoodSection {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(in.readLine());
		int[] arr = new int[L];
		
		int idx = 0;
		StringTokenizer st = new StringTokenizer(in.readLine());
		while (st.hasMoreTokens()) {
			arr[idx++] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int n = Integer.parseInt(in.readLine());
		
		for (int num : arr) {
			if (n == num) {
				System.out.println(0);
				return;
			}
		}
		
		int low = -1;
		int high = -1;
		
		if (n < arr[0]) {
			low = 1;
			high = arr[0] - 1;
		}
		else {
			for (int i = 0; i < L - 1; i++) {
				if (arr[i] <= n && arr[i+1] >= n) {
					low = arr[i] + 1;
					high = arr[i+1] - 1;
					break;
				}
			}
		}

		System.out.println((n - low) * (high - n + 1) + (high - n));
		
	}
}
//
//9 10 12
//8 - 10 11 12
//9 - 10 11 12
//10 - 11 12
//
//a b c
//a < b : (c - b + 1)
//a == b : (c - b)
//
//(n - low) * (high - n + 1) + (high - n)
