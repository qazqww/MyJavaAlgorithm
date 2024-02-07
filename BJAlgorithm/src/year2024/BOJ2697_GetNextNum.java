package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2697_GetNextNum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        loop: for (int t = 0; t < T; t++) {
            String num = in.readLine();
            int[] cnt = new int[10];
            int first = -1;
            for (int i = num.length() - 1; i > 0; i--) {
                cnt[num.charAt(i) - '0']++;
                if (num.charAt(i) > num.charAt(i - 1)) {
                    cnt[num.charAt(i - 1) - '0']++;
                    first = i - 1;
                    break;
                }
                if (i == 1) {
                    System.out.println("BIGGEST");
                    continue loop;
                }
            }

            StringBuilder answer = new StringBuilder(num.substring(0, first));
            for (int i = num.charAt(first) - '0' + 1; i < 10; i++) {
                if (cnt[i] > 0) {
                    answer.append(i);
                    cnt[i]--;
                    break;
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < cnt[i]; j++) {
                    answer.append(i);
                }
            }
            System.out.println(answer);
        }
    }
}