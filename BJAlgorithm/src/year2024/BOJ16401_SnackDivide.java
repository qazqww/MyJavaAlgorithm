package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16401_SnackDivide {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int P = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] snacks = new int[S];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < S; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1;
        int end = 1_000_000_000;
        int answer = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            int[] temp = new int[S];
            System.arraycopy(snacks, 0, temp, 0, S);
            int cnt = 0;
            boolean canDivide = false;
            for (int j = 0; j < S; j++) {
                if (temp[j] >= mid) {
                    cnt += temp[j] / mid;
                    if (cnt >= P) {
                        canDivide = true;
                        break;
                    }
                }
            }
            if (canDivide) {
                start = mid + 1;
                answer = mid;
            }
            else {
                end = mid - 1;
            }
        }
        System.out.println(answer);
    }
}