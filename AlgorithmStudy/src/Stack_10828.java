// https://www.acmicpc.net/problem/10828

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stack_10828 {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] myStack = new int[10000];
		int index = 0;
		
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			String[] command = in.readLine().split(" ");
			
			switch (command[0]) {
			case "push":
				myStack[index] = Integer.parseInt(command[1]);
				index++;
				break;
				
			case "pop":
				if (index == 0) {
					System.out.println("-1");
					break;
				}
				System.out.println(myStack[--index]);
				break;
				
			case "size":
				System.out.println(index);
				break;
				
			case "empty":
				if (index == 0) {
					System.out.println("1");
				}
				else {
					System.out.println("0");
				}
				break;
				
			case "top":
				if (index == 0) {
					System.out.println("-1");
					break;
				}
				System.out.println(myStack[index - 1]);
				break;
			}
		}
	}
}
