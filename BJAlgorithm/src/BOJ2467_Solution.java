// https://www.acmicpc.net/problem/2467

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2467_Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++)  {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int first = 0;
		int last = N - 1;
		int min = Integer.MAX_VALUE;
		int[] answer = new int[2];
		
		while (first < last) {
			int result = arr[last] + arr[first];
			if (Math.abs(result) < Math.abs(min)) {
				min = result;
				answer[0] = arr[first];
				answer[1] = arr[last];
			}
			if (result < 0) {
				first++;
			}
			else {
				last--;
			}
		}
		
		System.out.println(answer[0] + " " + answer[1]);
	}
}