// https://www.acmicpc.net/problem/14719

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ14719_Rainwater {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		Stack<int[]> stack = new Stack<>();	// int[] => { index, height }
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < C; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if (stack.isEmpty()) {
				stack.push(new int[] { i, num });
				continue;
			}
			
			if (stack.peek()[1] >= num) {
				stack.push(new int[] { i, num });
			}
			else {
				while (!stack.isEmpty() && stack.peek()[1] < num) {
					int[] poped = stack.pop();
					
					if (stack.isEmpty())
						break;
					else
						answer += (Math.min(stack.peek()[1], num) - poped[1]) * (i - stack.peek()[0] - 1);
				}
				
				stack.push(new int[] { i, num });
			}
		}
		
		System.out.println(answer);
	}
}