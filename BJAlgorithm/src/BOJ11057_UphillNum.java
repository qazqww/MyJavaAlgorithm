// https://www.acmicpc.net/problem/11057

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11057_UphillNum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long[] nums = new long[10];
		long[] results = new long[10];
		Arrays.fill(nums, 1);
		
		for (int i = 0; i < N - 1; i++) {
			Arrays.fill(results, 0);
			for (int j = 0; j < 10; j++) {
				for (int k = j; k < 10; k++) {
					results[k] += nums[j];
					results[k] %= 10007;
				}
			}
			for (int j = 0; j < 10; j++) {
				nums[j] = results[j];
			}
		}
		
		long answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += nums[i];
			answer %= 10007;
		}
		System.out.println(answer);
	}
}