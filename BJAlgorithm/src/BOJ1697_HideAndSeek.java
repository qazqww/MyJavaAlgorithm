// https://www.acmicpc.net/problem/1697

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1697_HideAndSeek {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int size = Math.max(start, end) * 2 > 100_000 ? 100_000 : Math.max(start, end) * 2;
		int[] dp = new int[size + 1];
		Arrays.fill(dp, 100_000_000);
		for (int i = 0; i < dp.length; i++) {
			dp[i] = Math.abs(start - i);
		}
		
		for (int i = 0; i < dp.length; i++) {
			if (i % 2 == 0 && dp[i / 2] + 1 < dp[i])
				dp[i] = dp[i / 2] + 1;
			if (i + 1 < dp.length && dp[i + 1] + 1 < dp[i])
				dp[i] = dp[i + 1] + 1;
			
			if (i * 2 < dp.length && dp[i * 2] > dp[i] + 1)
				dp[i * 2] = dp[i] + 1;
			if (i + 1 < dp.length && dp[i + 1] > dp[i] + 1)
				dp[i + 1] = dp[i] + 1;
		}

		System.out.println(dp[end]);
	}
}

/* BFS 풀이
public class BOJ12851_HideAndSeek2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[100_001];
		Arrays.fill(arr, 100_000_000);
		arr[start] = 0;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int num = queue.poll();
			
			if (num + 1 < arr.length && arr[num + 1] > arr[num] + 1) {
				arr[num + 1] = arr[num] + 1;
				queue.offer(num + 1);
			}
			
			if (num - 1 >= 0 && arr[num - 1] > arr[num] + 1) {
				arr[num - 1] = arr[num] + 1;
				queue.offer(num - 1);
			}
			
			if (num * 2 < arr.length && arr[num * 2] > arr[num] + 1) {
				arr[num * 2] = arr[num] + 1;
				queue.offer(num * 2);
			}
		}
		
		System.out.println(arr[end]);
	}
}
*/