// https://www.acmicpc.net/problem/14888

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888_OperInsert {
	static int[] arr, op;
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		op = new int[4];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		operation(1, arr[0]);
		System.out.println(max + "\n" + min);
	}
	
	static void operation(int index, int num) {
		if (index == N) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (op[i] == 0) {
				continue;
			}
			
			op[i]--;
			switch(i) {
			case 0:
				operation(index + 1, num + arr[index]);
				break;
			case 1:
				operation(index + 1, num - arr[index]);
				break;
			case 2:
				operation(index + 1, num * arr[index]);
				break;
			case 3:
				if (num < 0 && arr[index] > 0) {
					operation(index + 1, -(-num / arr[index]));
				}
				operation(index + 1, num / arr[index]);
				break;
			}
			op[i]++;
		}
	}
}