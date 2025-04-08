package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1117_Paint1 {
    static long W, H, f, c, x1, y1, x2, y2;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        setup(in.readLine());
        long xSum = 0;

        if (f >= x2) {
            xSum += 2 * (x2 - x1);
        }
        else if (f <= x1) {
            xSum += x2 - x1;
        }
        else {
            xSum += 2 * (f - x1) + x2 - f;
        }

        System.out.println(W * H - xSum * (y2 - y1) * (c+1));
    }

    static void setup(String str) {
        StringTokenizer st = new StringTokenizer(str);
        W = Long.parseLong(st.nextToken());
        H = Long.parseLong(st.nextToken());
        f = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());
        x1 = Long.parseLong(st.nextToken());
        y1 = Long.parseLong(st.nextToken());
        x2 = Long.parseLong(st.nextToken());
        y2 = Long.parseLong(st.nextToken());
        f = Math.min(f, W - f);
    }
}