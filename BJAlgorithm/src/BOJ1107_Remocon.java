// https://www.acmicpc.net/problem/1107

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1107_Remocon {
	
	static String N;
	static int[] btns;
	static int answer = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = in.readLine();
		int M = Integer.parseInt(in.readLine());
		answer = Math.abs(Integer.parseInt(N) - 100);
		
		if (M == 0) {
			System.out.println(Math.min(answer, N.length()));
			return;
		}
		
		Set<Integer> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(in.readLine());
		while (st.hasMoreTokens()) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		btns = new int[10 - M];
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			if (!set.contains(i))
				btns[idx++] = i;
		}
		
		for (int i = 0; i < btns.length; i++) {
			permu(btns[i]);
		}
		
		System.out.println(answer);
	}
	
	static void permu(int num) {
		
		answer = Math.min(answer, Integer.toString(num).length() + Math.abs(Integer.parseInt(N) - num));
		
		if (num == 0)
			return;
		
		num *= 10;
		for (int i = 0; i < btns.length; i++) {
			if (num <= 1000000)
				permu(num + btns[i]);
		}
	}
}
