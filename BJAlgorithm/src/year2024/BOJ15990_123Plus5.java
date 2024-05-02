package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15990_123Plus5 {
    static final int MOD = 1_000_000_009;
    static long[][] result = new long[100_001][3];
    public static void main(String[] args) throws IOException {
        solve();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(in.readLine());
            System.out.println((result[num][0] + result[num][1] + result[num][2]) % MOD);
        }
    }

    static void solve() {
        result[1][0] = 1;
        result[2][1] = 1;
        result[3][0] = 1;
        result[3][1] = 1;
        result[3][2] = 1;

        for (int i = 4; i < result.length; i++) {
            result[i][0] = (result[i - 1][1] % MOD + result[i - 1][2] % MOD) % MOD;
            result[i][1] = (result[i - 2][0] % MOD + result[i - 2][2] % MOD) % MOD;
            result[i][2] = (result[i - 3][0] % MOD + result[i - 3][1] % MOD) % MOD;
        }
    }
}