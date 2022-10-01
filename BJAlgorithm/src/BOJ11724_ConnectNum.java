// https://www.acmicpc.net/problem/11724

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ11724_ConnectNum {
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			
			union(u, v);
		}
		
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(findRoot(parents[i]));
		}
		
		System.out.println(set.size());
	}
	
	static int findRoot(int num) {
		if (parents[num] == num)
			return num;
		else
			return parents[num] = findRoot(parents[num]);
	}
	
	static void union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		parents[bRoot] = aRoot;
	}
}