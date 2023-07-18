package B5toS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1676_FactorialZeroCnt {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int two = 0;
        int five = 0;

        for (int i = 2; i <= N; i++) {
            int num = i;
            while (num % 2 == 0) {
                num /= 2;
                two++;
            }
            while (num % 5 == 0) {
                num /= 5;
                five++;
            }
        }

        System.out.println(Math.min(two, five));
    }
}