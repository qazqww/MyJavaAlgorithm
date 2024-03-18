package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14728_Cramming {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] knapsack = new int[2][T + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            for (int t = time; t < T + 1; t++) {
                knapsack[1][t] = Math.max(knapsack[0][t], knapsack[0][t - time] + score);
            }
            System.arraycopy(knapsack[1], 0, knapsack[0], 0, knapsack[0].length);
        }
        System.out.println(knapsack[0][T]);
    }
}