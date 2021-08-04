// https://www.acmicpc.net/problem/10866

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Deque_10866 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] myDeque = new int[20000];
		
		// 	myDeque
		// 	[]		[]		[]		[]
		//	start	num1	num2	end
		int start = 9999;
		int end = 10000;
		
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			String[] command = in.readLine().split(" ");
			
			switch (command[0]) {
			case "push_front":
				myDeque[start] = Integer.parseInt(command[1]);
				start--;
				break;
			case "push_back":
				myDeque[end] = Integer.parseInt(command[1]);
				end++;
				break;
				
			case "pop_front":
				if (end - start == 1) {
					System.out.println("-1");
					break;
				}
				System.out.println(myDeque[++start]);
				break;
			case "pop_back":
				if (end - start == 1) {
					System.out.println("-1");
					break;
				}
				System.out.println(myDeque[--end]);
				break;
				
			case "size":
				System.out.println(end - start - 1);
				break;
				
			case "empty":
				int isEmpty = (end - start == 1) ? 1 : 0;
				System.out.println(isEmpty);
				break;
				
			case "front":
				if (end - start == 1) {
					System.out.println("-1");
					break;
				}
				System.out.println(myDeque[start+1]);
				break;
				
			case "back":
				if (end - start == 1) {
					System.out.println("-1");
					break;
				}
				System.out.println(myDeque[end-1]);
				break;
			}
			
			if (start == end) {
				end++;
			}
		}
	}
}
