// https://www.acmicpc.net/problem/11659

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11659_PrefixSum4 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(in.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
			
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int min = Integer.parseInt(st.nextToken()) - 1;
			int max = Integer.parseInt(st.nextToken()) - 1;
			System.out.println(min == 0 ? arr[max] : arr[max] - arr[min - 1]);
		}
	}
}
