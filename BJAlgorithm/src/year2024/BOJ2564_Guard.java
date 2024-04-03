package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2564_Guard {
    static int MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int C = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        MAX = 2 * (R + C);
        int storeNum = Integer.parseInt(in.readLine());
        int[] storePos = new int[storeNum + 1];
        for (int i = 0; i < storeNum + 1; i++) {
            st = new StringTokenizer(in.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());
            switch (dir) {
                case 1: // 북
                    storePos[i] = pos;
                    break;
                case 2: // 남
                    storePos[i] = MAX - R - pos;
                    break;
                case 3: // 서
                    storePos[i] = MAX - pos;
                    break;
                case 4: // 동
                    storePos[i] = C + pos;
                    break;
            }
        }

        int result = 0;
        for (int store : storePos) {
            result += getDist(storePos[storeNum], store);
        }
        System.out.println(result);
    }

    static int getDist(int cur, int other) {
        int big = Math.max(cur, other);
        int small = Math.min(cur, other);
        return Math.min(big - small, (MAX - big) + small);
    }
}