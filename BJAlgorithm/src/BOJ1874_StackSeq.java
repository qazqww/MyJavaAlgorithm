import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1874_StackSeq {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		int idx = 0;
		int cur = 1;
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		boolean isPossible = true;
		while (idx < n) {
			if (arr[idx] > cur) {
				while (cur <= arr[idx]) {
					stack.push(cur++);
					sb.append("+\n");
				}
				stack.pop();
				sb.append("-\n");
			}
			else if (arr[idx] < cur) {
				if (stack.peek() == arr[idx]) {
					stack.pop();
					sb.append("-\n");
				}
				else {
					isPossible = false;
					break;
				}
			}
			else {
				sb.append("+\n-\n");
				cur++;
			}
			idx++;
		}
		sb.setLength(sb.length() - 1);
		System.out.println(isPossible ? sb : "NO");
		
	}
}
