package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1337_RightArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }

        Arrays.sort(arr);
        int maxCnt = 1;
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int idx = i;
            int cnt = 1;
            while (idx + 1 < N && arr[idx + 1] < num + 5) {
                idx++;
                cnt++;
            }
            maxCnt = Math.max(maxCnt, cnt);
        }

        System.out.println(5 - maxCnt);
    }
}
