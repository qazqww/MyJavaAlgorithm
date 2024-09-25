package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15824_SpringCapsaicin {
    static long[] squareValue;
    final static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        long[] level = new long[N];
        squareValue = new long[N];
        getSquare();

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(level);

        long answer = 0;
        for (int i = 0; i < N; i++) {
            long result = level[i] * (squareValue[i] - 1) - level[i] * (squareValue[N - i - 1] - 1);
            answer += result % MOD + MOD;
            answer %= MOD;
        }
        System.out.println(answer);
    }

    static void getSquare() {
        long val = 1;
        for (int i = 0; i < squareValue.length; i++) {
            squareValue[i] = val;
            val *= 2;
            val %= MOD;
        }
    }
}