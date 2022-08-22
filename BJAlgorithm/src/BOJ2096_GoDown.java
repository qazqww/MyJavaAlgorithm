// https://www.acmicpc.net/problem/2096

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096_GoDown {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] max = new int[3];
		int[] min = new int[3];
		int[] target = new int[3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 3; j++) {
				target[j] = Integer.parseInt(st.nextToken());
			}
			
			int temp0 = target[0] + Math.max(max[0], max[1]);
			int temp1 = target[1] + Math.max(Math.max(max[0], max[1]), max[2]);
			int temp2 = target[2] + Math.max(max[1], max[2]);

			max[0] = temp0;
			max[1] = temp1;
			max[2] = temp2;
			
			temp0 = target[0] + Math.min(min[0], min[1]);
			temp1 = target[1] + Math.min(Math.min(min[0], min[1]), min[2]);
			temp2 = target[2] + Math.min(min[1], min[2]);
			
			min[0] = temp0;
			min[1] = temp1;
			min[2] = temp2;
		}

		System.out.print(Math.max(Math.max(max[0], max[1]), max[2])
				+ " " + Math.min(Math.min(min[0], min[1]), min[2]));
	}
}