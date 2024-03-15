package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15810_BalloonFactory {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] times = new int[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        long answer = -1;
        long start = 1L;
        long end = 1_000_000_000_000L;
        while (start <= end) {
            long mid = (start + end) / 2;
            long balloons = 0;
            for (int i = 0; i < N; i++) {
                balloons += mid / times[i];
            }
            if (balloons >= M) {
                end = mid - 1;
                answer = mid;
            }
            else {
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }
}