// https://www.acmicpc.net/problem/1991

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1991_TreeTraversal {

	static Map<Character, Node> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			map.put(st.nextToken().charAt(0), new Node(st.nextToken().charAt(0), st.nextToken().charAt(0)));
		}
		
		order('A', 0);
		System.out.println();
		order('A', 1);
		System.out.println();
		order('A', 2);
	}
	
	static void order(char head, int type) {
		
		if (head == '.')
			return;
		
		char left = map.get(head).left;
		char right = map.get(head).right;
		
		switch (type) {
		case 0:
			System.out.print(head);
			order(left, type);
			order(right, type);
			break;
		case 1:
			order(left, type);
			System.out.print(head);
			order(right, type);
			break;
		case 2:
			order(left, type);
			order(right, type);
			System.out.print(head);
			break;
		}
	}
	
	static class Node {
		char left;
		char right;
		public Node(char left, char right) {
			this.left = left;
			this.right = right;
		}
	}
}

// 전위 순회 : 부모 -> 좌 -> 우
// 중위 순회 : 좌 -> 부모 -> 우
// 후위 순회 : 좌 -> 우 -> 부모