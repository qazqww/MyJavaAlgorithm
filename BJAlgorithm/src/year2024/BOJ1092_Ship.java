package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1092_Ship {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        int[] crain = new int[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            crain[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crain);
        int M = Integer.parseInt(in.readLine());
        int[] box = new int[M];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < M; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(box);

        int start = 0;
        int end = 10000;
        int answer = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (mid * crain.length < M) {
                start = mid + 1;
                continue;
            }
            int place = M - 1;
            boolean isPossible = true;
            loop: for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < mid; j++) {
                    if (box[place--] > crain[i]) {
                        isPossible = false;
                        break loop;
                    }
                    if (place < 0) {
                        break loop;
                    }
                }
            }
            if (isPossible) {
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