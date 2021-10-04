// https://www.acmicpc.net/problem/11053

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11053_LIS {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[] arr = new int[N];
		int[] len = new int[N];
		Arrays.fill(len, 1);
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			
			for (int j = 0; j < i; j++) {
				if (arr[j] < num && len[i] <= len[j])
					len[i] = len[j] + 1;
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++)
			if (len[i] > max) max = len[i];
		System.out.println(max);
	}
}