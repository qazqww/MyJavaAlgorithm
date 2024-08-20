package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10836_QueenBee {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] worm = new int[M][];
        worm[0] = new int[M];
        Arrays.fill(worm[0], 1);
        for (int i = 1; i < M; i++) {
            worm[i] = new int[1];
            worm[i][0] = 1;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int[] plus = new int[3];
            for (int j = 0; j < 3; j++) {
                plus[j] = Integer.parseInt(st.nextToken());
            }
            int idx = 0;

            int r = M - 1;
            int c = 0;
            for (; r >= 0; r--) {
                while (plus[idx] == 0) {
                    idx++;
                }
                worm[r][c] += idx;
                plus[idx]--;
            }
            r++;
            c++;
            for (; c < M; c++) {
                while (plus[idx] == 0) {
                    idx++;
                }
                worm[r][c] += idx;
                plus[idx]--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < M; r++) {
            sb.append(worm[r][0]).append(" ");
            for (int c = 1; c < M; c++) {
                sb.append(worm[0][c]).append(" ");
            }
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}
/*
0   0   1   1   1   2   2

3~5 3~6 3~7
2~5 2~6 2~7
1~5 1~6 1~7

2M-1
M-1 M M+1

700*700*1_000_000
49_000_000_000
 */