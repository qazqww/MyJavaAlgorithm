// https://www.acmicpc.net/problem/5639

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5639_BinarySearchTree {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str = in.readLine();
		if (str == null || str.isEmpty())
			return;
		Node head = new Node(Integer.parseInt(str));
		
		while (true) {
			str = in.readLine();
			if (str == null || str.isEmpty())
				break;
			
			int num = Integer.parseInt(str);
			Node cur = head;
			
			while (true) {
				if (num < cur.value) {
					if (cur.left == null) {
						cur.left = new Node(num);
						break;
					}
					else {
						cur = cur.left;
					}
				}
				else if (num > cur.value) {
					if (cur.right == null) {
						cur.right = new Node(num);
						break;
					}
					else {
						cur = cur.right;
					}
				}
			}
		}
		
		postOrder(head);
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void postOrder(Node cur) {
		if (cur == null)
			return;
		
		postOrder(cur.left);
		postOrder(cur.right);
		sb.append(cur.value + "\n");
	}
	
	static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
}