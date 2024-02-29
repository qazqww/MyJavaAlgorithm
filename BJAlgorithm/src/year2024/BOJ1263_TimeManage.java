package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1263_TimeManage {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        boolean[] used = new boolean[1_000_000];
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int T = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            int remain = 0;
            answer = Math.min(answer, S - T);
            if (T > S) {
                System.out.println(-1);
                return;
            }
            for (int j = S - T; j < S; j++) {
                if (used[j]) {
                    remain++;
                }
                else {
                    used[j] = true;
                }
            }
            int time = S - T - 1;
            while (remain > 0) {
                if (time < 0) {
                    System.out.println(-1);
                    return;
                }
                if (!used[time]) {
                    used[time] = true;
                    remain--;
                }
                answer = Math.min(answer, time);
                time--;
            }
        }
        System.out.println(answer);
    }
}