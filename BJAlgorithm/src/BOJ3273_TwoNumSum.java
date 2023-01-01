// https://www.acmicpc.net/problem/3273

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3273_TwoNumSum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[] nums = new int[n];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int i = 0;
		while (st.hasMoreTokens()) {
			nums[i++] = Integer.parseInt(st.nextToken());
		}
		
		int x = Integer.parseInt(in.readLine());
		
		Arrays.sort(nums);
		
		int front = 0;
		int rear = n-1;
		int answer = 0;
		
		while (front < rear) {
			int now = nums[front] + nums[rear];
			if (now == x) {
				answer++;
				front++;
				rear--;
			}
			else if (now > x) {
				rear--;
			}
			else if (now < x) {
				front++;
			}
		}
		
		System.out.println(answer);
	}
}