package B5toS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ4949_BalancedWorld {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = in.readLine();
            if (str.equals("."))
                break;

            Stack<Character> stack = new Stack<>();
            boolean isBalanced = true;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                if (ch == '(' || ch == '[') {
                    stack.push(ch);
                }
                else if (ch == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        isBalanced = false;
                        break;
                    }
                    else {
                        stack.pop();
                    }
                }
                else if (ch == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        isBalanced = false;
                        break;
                    }
                    else {
                        stack.pop();
                    }
                }
            }

            if (!stack.isEmpty())
                isBalanced = false;

            System.out.println(isBalanced ? "yes" : "no");
        }
    }
}
