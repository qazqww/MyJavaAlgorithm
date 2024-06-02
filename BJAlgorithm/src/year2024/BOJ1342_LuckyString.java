package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1342_LuckyString {
    static int strLen, answer, max = 1;
    static ArrayList<Integer> cnt = new ArrayList<>();
    static ArrayList<Character> order = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        strLen = str.length();
        for (char ch : str.toCharArray()) {
            if (order.contains(ch)) {
                int idx = order.indexOf(ch);
                cnt.set(idx, cnt.get(idx) + 1);
                max = cnt.get(idx);
            }
            else {
                order.add(ch);
                cnt.add(1);
            }
        }

        if (max == 1) {
            for (int i = 1; i <= strLen; i++) {
                max *= i;
            }
            System.out.println(max);
            return;
        }

        makeStr(' ', 0);
        System.out.println(answer);
    }

    static void makeStr(char pre, int len) {
        if (len >= strLen) {
            answer++;
            return;
        }

        for (int i = 0; i < cnt.size(); i++) {
            char next = order.get(i);
            if (cnt.get(i) <= 0 || next == pre) {
                continue;
            }
            cnt.set(i, cnt.get(i) - 1);
            makeStr(next, len + 1);
            cnt.set(i, cnt.get(i) + 1);
        }
    }
}