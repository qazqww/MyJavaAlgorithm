// https://www.acmicpc.net/problem/18111

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111_Minecraft {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N*M];
		int idx = 0;
		int max = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				arr[idx] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[idx]);
				min = Math.min(min, arr[idx]);
				idx++;
			}
		}
		
		int minTime = Integer.MAX_VALUE;
		int height = -1;
		for (int i = min; i <= max; i++) {
			
			int time = 0;
			int inven = B;
			
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] > i) {
					time += (arr[j] - i) * 2;
					inven += (arr[j] - i);
				}
				else if (arr[j] < i) {
					time += i - arr[j];
					inven -= i - arr[j];
				}
			}
			
			if (inven >= 0 && time <= minTime) {
				minTime = time;
				height = i;
			}
		}
		
		System.out.println(minTime + " " + height);
	}
}