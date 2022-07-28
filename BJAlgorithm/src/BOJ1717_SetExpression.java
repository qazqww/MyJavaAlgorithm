// https://www.acmicpc.net/problem/1717

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717_SetExpression {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parent[i] = i;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (op == 0) {
				union(a, b);
			}
			else {
				System.out.println(sameCheck(a, b) ? "YES" : "NO");
			}
		}
	}
	
	static int findRoot(int num) {
		if (parent[num] == num)
			return num;
		
		return parent[num] = findRoot(parent[num]);
	}
	
	static boolean sameCheck(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		
		if (aRoot == bRoot)
			return true;
		else
			return false;
	}
	
	static void union(int a, int b) {
		if (!sameCheck(a, b))
			parent[findRoot(b)] = findRoot(a);
	}
}
