package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1662_Compression {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        int len = 0;
        Stack<Integer> repeatStack = new Stack<>();
        Stack<Integer> lenStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '(':
                    repeatStack.push(str.charAt(i - 1) - '0');
                    lenStack.push(--len);
                    len = 0;
                    break;
                case ')':
                    len = len * repeatStack.pop() + lenStack.pop();
                    break;
                default:
                    len++;
                    break;
            }
        }
        System.out.println(len);
    }
}