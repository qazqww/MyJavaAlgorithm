package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ1490_DivideByDigits {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String targetStr = in.readLine();
        Set<Integer> digits = new HashSet<>();
        int max = -1;
        for (char ch : targetStr.toCharArray()) {
            digits.add(ch - '0');
            max = Math.max(max, ch - '0');
        }

        if (digits.contains(9)) {
            digits.remove(3);
        }
        if (digits.contains(8)) {
            digits.remove(4);
            digits.remove(2);
        }
        if (digits.contains(6)) {
            digits.remove(3);
            digits.remove(2);
        }
        if (digits.contains(4)) {
            digits.remove(2);
        }
        digits.remove(1);
        digits.remove(0);

        long target = Long.parseLong(targetStr);
        long range = 1;
        while (true) {
            for (long i = target * range, step = 1; i < target * range + range; i += step) {
                if (i % max == 0) {
                    step = max;
                }
                boolean isCorrect = true;
                for (int digit : digits) {
                    if (i % digit != 0) {
                        isCorrect = false;
                        break;
                    }
                }
                if (isCorrect) {
                    System.out.println(i);
                    return;
                }
            }
            range *= 10;
        }
    }
}