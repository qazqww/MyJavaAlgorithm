// https://www.acmicpc.net/problem/11722

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ11722_LongestDecreasing {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		// 순서를 거꾸로 한 뒤 LIS 풀이법을 적용
		ArrayList<Integer> nums = new ArrayList<>();
		for (int i = 0; i < N; i++)
			nums.add(Integer.parseInt(st.nextToken()));
		Collections.reverse(nums);
		
		int[] dp = new int[N];
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums.get(j) < nums.get(i) && dp[i] < dp[j] + 1)
					dp[i] = dp[j] + 1;
			}
			
			if (max < dp[i])
				max = dp[i];
		}
		System.out.println(max);
		
		/*
		ArrayList<Integer> dp = new ArrayList<>();
		dp.add(nums.get(0));
		
		for (int i = 1; i < N; i++) {
			int num = nums.get(i);
			if (num > dp.get(dp.size() - 1))
				dp.add(num);
			else {
				for (int j = 0; j < dp.size(); j++) {
					if (num <= dp.get(j))
						dp.set(j, num);
				}
			}
		}
		
		System.out.println(dp.size());*/
	}
}