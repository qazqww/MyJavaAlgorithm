// https://www.acmicpc.net/problem/4195

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ4195_FriendNetwork {

	static Map<String, String> relation;
	static Map<String, Integer> count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			int F = Integer.parseInt(in.readLine());
			relation = new HashMap<>();
			count = new HashMap<>();
			
			for (int f = 0; f < F; f++) {
				st = new StringTokenizer(in.readLine());
				String first = st.nextToken();
				String second = st.nextToken();
				
				if (!relation.containsKey(first)) {
					count.put(first, 1);
					relation.put(first, first);
				}
				
				if (!relation.containsKey(second)) {
					count.put(second, 1);
					relation.put(second, second);
				}
				
				union(first, second);

				System.out.println(count.get(find(first)));
			}
		}
	}
	
	static String find(String name) {
		String parent = relation.get(name);
		
		if (parent.equals(name))
			return name;
		
		String root = find(parent);
		relation.put(name, root);
		return root;
	}
	
	static void union(String a, String b) {
		String aRoot = find(a);
		String bRoot = find(b);
		
		if (aRoot.equals(bRoot))
			return;
		
		relation.put(bRoot, aRoot);
		count.put(aRoot, count.get(aRoot) + count.get(bRoot));
		count.remove(bRoot);
	}
}