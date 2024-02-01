package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477_RestBuild {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] points = new int[N + 1];
        points[N] = L;

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            points[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(points);
        int maxGap = points[0];
        for (int i = N; i > 0; i--) {
            points[i] = points[i] - points[i - 1];
            maxGap = Math.max(maxGap, points[i]);
        }

        int start = 1;
        int end = maxGap;
        int answer = maxGap;
        while (start <= end) {
            int mid = (start + end) / 2;
            int cnt = 0;
            for (int i = 0; i < points.length; i++) {
                cnt += points[i] / mid;
                if (points[i] % mid == 0) {
                    cnt--;
                }
            }
            if (cnt <= M) {
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