package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14627_GreenOnionChicken {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int S = Integer.parseInt(temp[0]);
        int C = Integer.parseInt(temp[1]);
        int[] greenOnion = new int[S];
        long start = 1;
        long end = Integer.MAX_VALUE;
        long used = 0;
        long sum = 0;
        for (int i = 0; i < S; i++) {
            greenOnion[i] = Integer.parseInt(in.readLine());
            sum += greenOnion[i];
        }

        while (start <= end) {
            long mid = (start + end) / 2;
            int canMake = 0;
            for (int greenonion : greenOnion) {
                canMake += greenonion / mid;
            }

            if (canMake >= C) {
                start = mid + 1;
                used = mid;
            }
            else {
                end = mid - 1;
            }
        }
        System.out.println(sum - used * C);
    }
}