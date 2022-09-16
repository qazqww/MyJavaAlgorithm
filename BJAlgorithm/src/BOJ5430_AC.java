// https://www.acmicpc.net/problem/5430

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5430_AC {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		loop: for (int t = 0; t < T; t++) {
			String command = in.readLine();
			int n = Integer.parseInt(in.readLine());
			String temp = in.readLine();
			temp = temp.substring(1, temp.length() - 1);
			
			int[] arr = new int[0];
			if (!temp.isEmpty()) {
				String[] strArr = temp.split(",");
				
				arr = new int[strArr.length];
				for (int i = 0; i < strArr.length; i++) {
					arr[i] = Integer.parseInt(strArr[i]);
				}
			}
			
			boolean isFront = true;
			int front = 0;
			int back = arr.length - 1;
			for (int i = 0; i < command.length(); i++) {
				switch(command.charAt(i)) {
				case 'R':
					isFront = !isFront;
					break;
				case 'D':
					if (front > back) {
						System.out.println("error");
						continue loop;
					}
					
					if (isFront)
						front++;
					else
						back--;
					break;
				}
			}
			
			if (front > back) {
				System.out.println("[]");
			}
			else {
				StringBuilder sb = new StringBuilder("[");
				if (isFront) {
					for (int i = front; i <= back; i++)
						sb.append(arr[i] + ",");
				}
				else {
					for (int i = back; i >= front; i--)
						sb.append(arr[i] + ",");
				}
				sb.setCharAt(sb.length() - 1, ']');
				
				System.out.println(sb);
			}
		}
	}
}