package B5toS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10773_Zero {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(in.readLine());

        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(in.readLine());
            if (num == 0) {
                answer -= stack.peek();
                stack.pop();
            }
            else {
                answer += num;
                stack.push(num);
            }
        }

        System.out.println(answer);
    }
}
