package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1474_Underline {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] str = new String[N];
        int len = 0;
        for (int i = 0; i < N; i++) {
            str[i] = in.readLine();
            len += str[i].length();
        }
        int[] cnt = new int[N - 1];
        int underline = M - len;
        int value = underline / (N - 1);
        int remained = underline % (N - 1);
        Arrays.fill(cnt, value);

        if (remained != 0) {
            for (int i = 1; i < N; i++) {
                if (str[i].charAt(0) >= 'a' && str[i].charAt(0) <= 'z') {
                    cnt[i - 1]++;
                    remained--;
                    if (remained == 0) {
                        break;
                    }
                }
            }
        }
        if (remained != 0) {
            for (int i = N - 1; i > 0; i--) {
                if (str[i].charAt(0) >= 'A' && str[i].charAt(0) <= 'Z') {
                    cnt[i - 1]++;
                    remained--;
                    if (remained == 0) {
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - 1; i++) {
            sb.append(str[i]);
            for (int j = 0; j < cnt[i]; j++) {
                sb.append('_');
            }
        }
        sb.append(str[N - 1]);
        System.out.println(sb);
    }
}