// https://www.acmicpc.net/submit/1240/49080256

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1253_GOOD {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		int answer = 0;
		
		loop: for (int i = 0; i < N; i++) {
			int target = nums[i];
			int start = 0;
			int end = nums.length - 1;
			
			while (start < end) {
				if (start == i)
					start++;
				else if (end == i)
					end--;
				else {
					int sum = nums[start] + nums[end];
					if (sum == target) {
						answer++;
						continue loop;
					}
					
					else if (sum > target) {
						end--;
					}
					else {
						start++;
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}