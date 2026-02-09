package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473_ThreeSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        long[] solutions = new long[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        long[] answer = new long[3];
        long result = Long.MAX_VALUE;
        Arrays.sort(solutions);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long sum2 = solutions[i] + solutions[j];

                int start = 0;
                int end = N - 1;
                int canAnswer = 0;
                long tempResult = Long.MAX_VALUE;
                while (start < end) {
                    int mid = (start + end) / 2;
                    long sum3 = sum2 + solutions[mid];

                    if (solutions[mid] + sum2 == 0) {
                        canAnswer = mid;
                        break;
                    }
                    else if (solutions[mid] + sum2 > 0) {
                        if (Math.abs(sum3) < tempResult) {
                            canAnswer = mid;
                            tempResult = Math.abs(sum3);
                        }
                        end = mid - 1;
                    }
                    else if (solutions[mid] + sum2 < 0) {
                        if (Math.abs(sum3) < tempResult) {
                            canAnswer = mid;
                            tempResult = Math.abs(sum3);
                        }
                        start = mid + 1;
                    }
                }

                for (int k = canAnswer - 2; k <= canAnswer + 2; k++) {
                    if (k < 0 || k >= N || k == i || k == j) {
                        continue;
                    }

                    if (Math.abs(sum2 + solutions[k]) < result) {
                        result = Math.abs(sum2 + solutions[k]);
                        answer = new long[] { solutions[i], solutions[j], solutions[k] };
                    }
                }
            }
        }

        Arrays.sort(answer);
        System.out.printf("%d %d %d\n", answer[0], answer[1], answer[2]);
    }
}