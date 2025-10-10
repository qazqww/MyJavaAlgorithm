package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ20207_Calender {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        int[][] plans = new int[366][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            plans[start][0]++;
            plans[end][1]++;
        }

        int start = -1;
        int height = 0;
        int maxHeight = -1;
        int answer = 0;
        for (int i = 1; i < 366; i++) {
            if (plans[i][0] > 0) {
                if (start < 0) {
                    start = i;
                }
                height += plans[i][0];
                maxHeight = Math.max(maxHeight, height);
            }
            if (plans[i][1] > 0) {
                height -= plans[i][1];

                if (height == 0 && (i == 365 || plans[i + 1][0] == 0)) {
                    answer += maxHeight * (i - start + 1);
                    start = -1;
                    maxHeight = -1;
                }
            }
        }
        System.out.println(answer);
    }
}