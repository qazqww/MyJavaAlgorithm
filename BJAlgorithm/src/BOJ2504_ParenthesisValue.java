// https://www.acmicpc.net/problem/2504

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2504_ParenthesisValue {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();

        int lev = 0;
        Stack<int[]> numInfo = new Stack<>();
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            switch (ch) {
                case '(':
                case '[':
                    stack.push(ch);
                    lev++;
                    break;
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();

                        int val = 0;
                        while (!numInfo.isEmpty() && numInfo.peek()[0] == lev) {
                            val += numInfo.pop()[1];
                        }
                        lev--;
                        numInfo.push(new int[] { lev, val == 0 ? 2 : val * 2 });
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();

                        int val = 0;
                        while (!numInfo.isEmpty() && numInfo.peek()[0] == lev) {
                            val += numInfo.pop()[1];
                        }
                        lev--;
                        numInfo.push(new int[] { lev, val == 0 ? 3 : val * 3 });
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                    break;
            }
        }
        if (!stack.isEmpty()) {
            System.out.println(0);
        }
        else {
            int answer = 0;
            while (!numInfo.isEmpty()) {
                answer += numInfo.pop()[1];
            }
            System.out.println(answer);
        }
    }
}