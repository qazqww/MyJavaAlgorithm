package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2343_GuitarLesson {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] lessons = new int[N];

        st = new StringTokenizer(in.readLine());
        int max = 0;
        int total = 0;
        for (int i = 0; i < N; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, lessons[i]);
            total += lessons[i];
        }

        int start = max;
        int end = total;
        int answer = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                if (sum + lessons[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                sum += lessons[i];
            }

            if (cnt > M) {
                start = mid + 1;
            }
            else {
                answer = mid;
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }
}