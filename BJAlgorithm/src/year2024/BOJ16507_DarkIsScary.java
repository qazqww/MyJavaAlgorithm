package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16507_DarkIsScary {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[][] map = new int[R + 1][C + 1];
        for (int i = 1; i < R + 1; i++) {
            st = new StringTokenizer(in.readLine());
            int sum = 0;
            for (int j = 1; j < C + 1; j++) {
                sum += Integer.parseInt(st.nextToken());
                map[i][j] = sum + map[i - 1][j];
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(in.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            System.out.println((map[r2][c2] - map[r1][c2] - map[r2][c1] + map[r1][c1]) / ((r2 - r1) * (c2 - c1)));
        }
    }
}