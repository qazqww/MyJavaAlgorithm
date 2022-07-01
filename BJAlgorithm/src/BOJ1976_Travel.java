// https://www.acmicpc.net/problem/1976

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976_Travel {
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				boolean isLinked = Integer.parseInt(st.nextToken()) == 1 ? true : false;
				
				if (i >= j)
					continue;
				
				if (isLinked)
					union(i, j);
			}
		}

		st = new StringTokenizer(in.readLine());
		int base = findRoot(Integer.parseInt(st.nextToken()) - 1);
		boolean flag = true;
		
		for (int i = 1; i < M; i++) {
			if (base != findRoot(Integer.parseInt(st.nextToken()) - 1)) {
				flag = false;
				break;
			}
		}
		
		System.out.println(flag ? "YES" : "NO");
	}
	
	static int findRoot(int n) {
		return parents[n] == n ? n : findRoot(parents[n]);
	}
	
	static void union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		
		if (aRoot == bRoot)
			return;
		
		parents[aRoot] = bRoot;
	}
}