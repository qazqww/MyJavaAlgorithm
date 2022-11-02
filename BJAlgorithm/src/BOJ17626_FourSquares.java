// https://www.acmicpc.net/problem/17626

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ17626_FourSquares {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int max = (int)Math.sqrt(50000);
		int[] arr = new int[max];
		int[] cnt = new int[50001];
		Arrays.fill(cnt, 4);
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)Math.pow(i+1, 2);
			cnt[arr[i]] = 1;
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				int num = arr[i] + arr[j];
				
				if (num >= cnt.length)
					break;
				
				cnt[num] = Math.min(cnt[num], 2);
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] + arr[j] >= cnt.length)
					break;
				
				for (int k = 0; k < arr.length; k++) {
					int num = arr[i] + arr[j] + arr[k];
					
					if (num >= cnt.length)
						break;
					
					cnt[num] = Math.min(cnt[num], 3);
				}
			}
		}
		
		System.out.println(cnt[n]);
	}
}