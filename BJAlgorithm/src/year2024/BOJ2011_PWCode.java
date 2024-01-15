package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011_PWCode {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                if (i == 0 || str.charAt(i - 1) == '0' || str.charAt(i - 1) > '2') {
                    System.out.println(0);
                    return;
                }
            }
        }

        if (str.length() == 1) {
            System.out.println(1);
            return;
        }

        long[] dp = new long[str.length() + 1];
        dp[1] = 1;
        dp[2] = 1;

        if (str.charAt(1) != '0' && Integer.parseInt(str.substring(0, 2)) <= 26) {
            dp[2] = 2;
        }

        for (int i = 3; i < dp.length; i++) {
            if (str.charAt(i - 2) == '0' || Integer.parseInt(str.substring(i - 2, i)) > 26) {
                dp[i] = dp[i - 1] % 1_000_000;
                dp[i] %= 1_000_000;
            }
            else if (str.charAt(i - 1) == '0') {
                dp[i] += dp[i - 2] % 1_000_000;
                dp[i] %= 1_000_000;
            }
            else {
                dp[i] = dp[i - 1] % 1_000_000;
                dp[i] %= 1_000_000;
                dp[i] += dp[i - 2] % 1_000_000;
                dp[i] %= 1_000_000;
            }
        }

        System.out.println(dp[dp.length - 1]);
    }
}