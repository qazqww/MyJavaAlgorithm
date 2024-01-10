package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ2725_DotInSight {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(in.readLine());
        int max = 0;
        Set<Integer> testSet = new HashSet<>();
        for (int i = 0; i < C; i++) {
            int num = Integer.parseInt(in.readLine());
            testSet.add(num);
            max = Math.max(max, num);
        }

        Set<Double> set = new HashSet<>();
        for (int i = 1; i <= max; i++) {
            for (int y = 1; y <= i; y++) {
                set.add((double)y / i);
            }
            for (int x = 1; x <= i; x++) {
                set.add((double)i / x);
            }

            if (testSet.contains(i)) {
                System.out.println(set.size() + 2);
            }
        }
    }
}