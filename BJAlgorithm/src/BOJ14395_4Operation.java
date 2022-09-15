// https://www.acmicpc.net/problem/14395

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BOJ14395_4Operation {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		long s = Long.parseLong(temp[0]);
		long t = Long.parseLong(temp[1]);
		
		if (s == t) {
			System.out.println(0);
			return;
		}
		else if (t == 0) {
			System.out.println("-");
			return;
		}
		
		Map<Long, StringBuilder> memo = new HashMap<>();
		
		Queue<Info> queue = new LinkedList<>();
		queue.offer(new Info(s, new StringBuilder()));
		if (s != 0)
			queue.offer(new Info(1, new StringBuilder("/")));
		
		StringBuilder answer = new StringBuilder("*************************************************************");
		
		while(!queue.isEmpty()) {
			Info info = queue.poll();
			
			if (info.num == t) {
				if (compare(info.op, answer) <= 0)
					answer = info.op;
				continue;
			}
			
			if (info.num != 1 && info.num * info.num <= t) {
				StringBuilder sb = new StringBuilder(info.op + "*");
				if (memo.containsKey(info.num * info.num)) {
					if (compare(sb, memo.get(info.num * info.num)) < 0) {
						memo.put(info.num * info.num, sb);
						queue.offer(new Info(info.num * info.num, sb));
					}
				}
				else {
					memo.put(info.num * info.num, sb);
					queue.offer(new Info(info.num * info.num, sb));
				}
			}
			if (info.num * 2 <= t) {
				StringBuilder sb = new StringBuilder(info.op + "+");
				if (memo.containsKey(info.num * 2)) {
					if (compare(sb, memo.get(info.num * 2)) < 0) {
						memo.put(info.num * 2, sb);
						queue.offer(new Info(info.num * 2, sb));
					}
				}
				else {
					memo.put(info.num * 2, sb);
					queue.offer(new Info(info.num * 2, sb));
				}
			}
			
		}
		
		if (answer.toString().equals("*************************************************************"))
			System.out.println(-1);
		else
			System.out.println(answer);
	}
	
	static int compare(StringBuilder a, StringBuilder b) {
		if (a.equals(b))
			return 0;
		if (a.length() == b.length()) {
			for (int i = 0; i < a.length(); i++) {
				if (a.charAt(i) != b.charAt(i))
					return a.charAt(i) - b.charAt(i);
			}
		}
		return a.length() - b.length();
	}
	
	static class Info {
		long num;
		StringBuilder op;
		public Info(long num, StringBuilder op) {
			this.num = num;
			this.op = op;
		}
	}
}