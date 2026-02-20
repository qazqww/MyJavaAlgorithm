package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1072_Game {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long winRate = Y * 100 / X;

        if (winRate >= 99) {
            System.out.println(-1);
            return;
        }

        long min = 0;
        long max = Integer.MAX_VALUE;
        long answer = -1;
        while (min <= max) {
            long mid = (min + max) / 2;

            long newWinRate = (Y + mid) * 100 / (X + mid);
            if (newWinRate > winRate) {
                answer = mid;
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
        }
        System.out.println(answer);
    }
}