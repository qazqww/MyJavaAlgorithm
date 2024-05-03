package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2688_DontShrink {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        long[][] cnt = new long[65][10];
        for (int i = 0; i < 10; i++) {
            cnt[0][i] = 10 - i;
        }

        for (int i = 1; i < 65; i++) {
            long sum = 0;
            for (int j = 9; j >= 0; j--) {
                sum += cnt[i - 1][j];
                cnt[i][j] = sum;
            }
        }

        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(in.readLine()) - 1;
            System.out.println(cnt[num][0]);
        }
    }
}