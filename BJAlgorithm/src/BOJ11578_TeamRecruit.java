// https://www.acmicpc.net/problem/11578

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11578_TeamRecruit {
    static int[] player;
    static boolean[] selected;
    static int N, M;
    static int playerMin = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = (int)Math.pow(2, Integer.parseInt(st.nextToken())) - 1;
        M = Integer.parseInt(st.nextToken());
        player = new int[M];
        selected = new boolean[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int solve = 0;
            for (int j = 0; j < cnt; j++) {
                solve += (1 << (Integer.parseInt(st.nextToken()) - 1));
            }
            player[i] = solve;
        }

        subset(0);
        System.out.println(playerMin == 100_000 ? -1 : playerMin);
    }

    static void subset(int index) {
        if (index == M) {
            int result = 0;
            int cnt = 0;
            for (int i = 0; i < M; i++) {
                if (selected[i]) {
                    result |= player[i];
                    cnt++;
                }
            }

            if (result == N) {
                playerMin = Math.min(playerMin, cnt);
            }
            return;
        }

        selected[index] = false;
        subset(index + 1);
        selected[index] = true;
        subset(index + 1);
    }
}