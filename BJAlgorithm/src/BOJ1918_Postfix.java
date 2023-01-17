// https://www.acmicpc.net/problem/1918

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BOJ1918_Postfix {
	static int typeNum = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		
		PriorityQueue<OpInfo> pq = new PriorityQueue<>((a, b) -> b.order - a.order);
		ArrayList<CharInfo> list = new ArrayList<>();
		Map<Integer, Node> typeMap = new HashMap<>();
		int index = 0;
		int order = 10000;
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if (ch == '(') {
				order += 10000;
			}
			else if (ch == ')') {
				order -= 10000;
			}
			else {
				if (ch == '*' || ch == '/') {
					pq.offer(new OpInfo(ch, index, order + 5000));
				}
				else if (ch == '+' || ch == '-') {
					pq.offer(new OpInfo(ch, index, order));
				}
				list.add(new CharInfo(ch, -1));
				index++;
			}
			
			order--;
		}
		
		Node head = null;
		while (!pq.isEmpty()) {
			OpInfo op = pq.poll();

			Node leftNode = null;
			Node rightNode = null;
			
			CharInfo left = list.get(op.place - 1);
			if (left.type == -1) {
				leftNode = new Node(left.ch);
			}
			else {
				leftNode = typeMap.get(left.type);
			}
			
			CharInfo right = list.get(op.place + 1);
			if (right.type == -1) {
				rightNode = new Node(right.ch);
			}
			else {
				rightNode = typeMap.get(right.type);
			}

			Node parentNode = new Node(op.ch, leftNode, rightNode);
			typeMap.put(typeNum, parentNode);
			
			if (left.type == -1)
				list.get(op.place - 1).type = typeNum;
			else {
				int temp = left.type;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).type == temp)
						list.get(i).type = typeNum;
				}
			}
			
			if (right.type == -1)
				list.get(op.place + 1).type = typeNum;
			else {
				int temp = right.type;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).type == temp)
						list.get(i).type = typeNum;
				}
			}
			
			list.get(op.place).type = typeNum;
			
			head = parentNode;
			typeNum++;
		}
		
		postOrder(head);
	}
	
	static void postOrder(Node node) {
		if (node.left != null)
			postOrder(node.left);
		if (node.right != null)
			postOrder(node.right);
		System.out.print(node.val);
	}
	
	static class OpInfo {
		char ch;
		int place;
		int order;
		public OpInfo(char ch, int place, int order) {
			this.ch = ch;
			this.place = place;
			this.order = order;
		}
	}
	
	static class CharInfo {
		char ch;
		int type;
		public CharInfo(char ch, int type) {
			this.ch = ch;
			this.type = type;
		}
	}
	
	static class Node {
		char val;
		Node left;
		Node right;
		
		public Node(char val) {
			this.val = val;
			this.left = null;
			this.right = null;
		}
		
		public Node(char val, Node left, Node right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}