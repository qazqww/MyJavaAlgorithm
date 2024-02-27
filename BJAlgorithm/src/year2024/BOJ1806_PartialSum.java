package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806_PartialSum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0;
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            if (sum >= S) {
                while (sum >= S && start < N) {
                    sum -= nums[start++];
                }
                answer = Math.min(answer, i - start + 2);
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}