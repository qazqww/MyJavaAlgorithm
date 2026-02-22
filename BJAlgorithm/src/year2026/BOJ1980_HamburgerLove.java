package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1980_HamburgerLove {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int colaTime = Integer.MAX_VALUE;
        int maxHamburger = -1;
        for (int i = 0; i <= t / n; i++) {
            int time = t - i * n;
            if (colaTime > time % m) {
                colaTime = time % m;
                maxHamburger = i + time / m;
            }
            else if (colaTime == time % m) {
                maxHamburger = Math.max(maxHamburger, i + time / m);
            }
        }
        System.out.println(maxHamburger + " " + colaTime);
    }
}
