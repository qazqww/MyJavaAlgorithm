// https://www.acmicpc.net/problem/17298

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298_RightBigNum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] answer = new int[N];
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		Stack<Item> stack = new Stack<>();
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			while (!stack.isEmpty() && stack.peek().num < num) {
				Item temp = stack.pop();
				answer[temp.no] = num;
			}
			
			stack.add(new Item(i, num));
		}
		
		while (!stack.isEmpty()) {
			Item temp = stack.pop();
			answer[temp.no] = -1;
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(answer[i] + " ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static class Item {
		int no;
		int num;
		public Item(int no, int num) {
			this.no = no;
			this.num = num;
		}
	}
}