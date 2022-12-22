// https://www.acmicpc.net/problem/11054

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11054_LongestBitonic {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] ascArr = new int[N];
		int[] ascSum = new int[N];
		ascSum[0] = 1;
		ascArr[0] = nums[0];
		
		int idx = 1;
		int len = 1;
		
		for (int i = 1; i < N; i++) {
			int next = nums[i];
			
			if (next > ascArr[len - 1]) {
				ascArr[len] = next;
				len++;
			}
			else {
				for (int j = len - 1; j > 0; j--) {
					if (next < ascArr[j] && next > ascArr[j-1]) {
						ascArr[j] = next;
						break;
					}
				}
				
				if (next < ascArr[0])
					ascArr[0] = next;
			}
			
			ascSum[idx++] = len;
		}
		
		int[] revNums = new int[N];
		for (int i = 0; i < N; i++) {
			revNums[i] = nums[N-1-i];
		}
		
		ascArr = new int[N];
		int[] ascSum2 = new int[N];
		ascSum2[0] = 1;
		ascArr[0] = revNums[0];
		
		idx = 1;
		len = 1;
		
		for (int i = 1; i < N; i++) {
			int next = revNums[i];
			
			if (next > ascArr[len - 1]) {
				ascArr[len] = next;
				len++;
			}
			else {
				for (int j = len - 1; j > 0; j--) {
					if (next < ascArr[j] && next > ascArr[j-1]) {
						ascArr[j] = next;
						break;
					}
				}
				
				if (next < ascArr[0])
					ascArr[0] = next;
			}
			
			ascSum2[idx++] = len;
		}
		
		int answer = -1;
		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, ascSum[i] + ascSum2[N - 1 - i]);
		}
		System.out.println(answer - 1);
	}
}