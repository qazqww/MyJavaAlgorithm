package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ16564_HOSProgamer {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);
        int[] level = new int[N];
        for (int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(level);

        long min = 1;
        long max = 2_000_000_001;
        long answer = 0;
        while (min <= max) {
            long mid = (min + max) / 2;
            int canUp = K;
            for (int i = 0; i < N; i++) {
                if (level[i] >= mid) {
                    break;
                }
                canUp -= mid - level[i];
                if (canUp < 0) {
                    break;
                }
            }

            if (canUp >= 0) {
                min = mid + 1;
                answer = mid;
            }
            else {
                max = mid - 1;
            }
        }
        System.out.println(answer);
    }
}