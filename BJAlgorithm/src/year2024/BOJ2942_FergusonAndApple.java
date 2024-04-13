package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2942_FergusonAndApple {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int R = Integer.parseInt(temp[0]);
        int G = Integer.parseInt(temp[1]);

        int big = Math.max(R, G);
        int small = Math.min(R, G);
        while (small != 0) {
            int remained = big % small;
            big = small;
            small = remained;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= Math.sqrt(big); i++) {
            if (big % i == 0) {
                sb.append(String.format("%d %d %d\n", i, R / i, G / i));
                if (i * i != big) {
                    sb.append(String.format("%d %d %d\n", (big / i), R / (big / i), G / (big / i)));
                }
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}