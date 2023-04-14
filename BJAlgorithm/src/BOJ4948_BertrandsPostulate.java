// https://www.acmicpc.net/problem/4948

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ4948_BertrandsPostulate {
    static final int MAX = 123_456 * 2 + 1;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        boolean[] isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i < Math.sqrt(MAX); i++) {
            for (int j = i * 2; j < MAX; j += i) {
                isPrime[j] = false;
            }
        }

        while (true) {
            int num = Integer.parseInt(in.readLine());
            if (num == 0) {
                break;
            }

            int cnt = 0;
            for (int i = num + 1; i <= num * 2; i++) {
                if (isPrime[i]) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}