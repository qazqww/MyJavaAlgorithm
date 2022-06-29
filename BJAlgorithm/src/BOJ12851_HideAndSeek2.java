// https://www.acmicpc.net/problem/12851

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ12851_HideAndSeek2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[100_001];
		Arrays.fill(arr, 100_000_000);
		
		Queue<Integer> queue = new LinkedList<>();
		
		arr[start] = 0;
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int num = queue.poll();
			
			for (int i = num; i < arr.length; i *= 2) {
				if (arr[i] >= arr[num]) {
					arr[i] = arr[num];
				}
				
				if (i + 1 < arr.length && arr[i + 1] > arr[i] + 1) {
					arr[i + 1] = arr[i] + 1;
					queue.offer(i + 1);
				}
				
				if (i - 1 >= 0 && arr[i - 1] > arr[i] + 1) {
					arr[i - 1] = arr[i] + 1;
					queue.offer(i - 1);
				}
				
				if (i == 0)
					break;
			}
		}
		
		System.out.println(arr[end]);
	}
}