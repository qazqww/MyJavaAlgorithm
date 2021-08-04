// https://www.acmicpc.net/problem/10845

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Queue_10845 {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] myQueue = new int[10000];
		int start = 0;
		int end = 0;
		
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			String[] command = in.readLine().split(" ");
			
			switch (command[0]) {
			case "push":
				myQueue[end] = Integer.parseInt(command[1]);
				end++;
				break;
				
			case "pop":
				if (start == end) {
					System.out.println("-1");
					break;
				}
				System.out.println(myQueue[start++]);
				break;
				
			case "size":
				System.out.println(end - start);
				break;
				
			case "empty":
				int isEmpty = (start == end) ? 1 : 0;
				System.out.println(isEmpty);
				break;
				
			case "front":
				if (start == end) {
					System.out.println("-1");
					break;
				}
				System.out.println(myQueue[start]);
				break;
				
			case "back":
				if (start == end) {
					System.out.println("-1");
					break;
				}
				System.out.println(myQueue[end-1]);
				break;
			}
		}
	}
}
