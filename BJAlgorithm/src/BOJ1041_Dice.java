import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1041_Dice {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(in.readLine());
        long[] dice = new long[6];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < 6; i++) {
            dice[i] = Long.parseLong(st.nextToken());
        }

        if (N == 1) {
            long answer = 0L;
            long max = -1L;
            for (int i = 0; i < 6; i++) {
                answer += dice[i];
                max = Math.max(max, dice[i]);
            }
            System.out.println(answer - max);
            return;
        }

        long oneMin = Long.MAX_VALUE;
        long twoMin = Long.MAX_VALUE;
        long threeMin = Long.MAX_VALUE;

        for (int i = 0; i < 6; i++) {
            oneMin = Math.min(oneMin, dice[i]);
            for (int j = i + 1; j < 6; j++) {
                Set<Integer> set = new HashSet<>();
                set.add(i);
                set.add(j);
                if ((set.contains(0) && set.contains(5)) ||
                        (set.contains(1) && set.contains(4)) ||
                        (set.contains(2) && set.contains(3))) {
                    continue;
                }
                twoMin = Math.min(twoMin, dice[i] + dice[j]);
                for (int k = j + 1; k < 6; k++) {
                    set.add(k);
                    if ((set.contains(0) && set.contains(5)) ||
                            (set.contains(1) && set.contains(4)) ||
                            (set.contains(2) && set.contains(3))) {
                        set.remove(k);
                        continue;
                    }
                    set.remove(k);
                    threeMin = Math.min(threeMin, dice[i] + dice[j] + dice[k]);
                }
            }
        }

        long answer = 0L;
        long three = 4L;
        long two = (N - 1) * 4 + (N - 2) * 4;
        long one = (N - 1) * (N - 2) * 4 + (N - 2) * (N - 2);
        answer += three * threeMin;
        answer += two * twoMin;
        answer += one * oneMin;

        System.out.println(answer);
    }
}