package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ26019_ImprovingIT {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] sellTable = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            int originPrice = Integer.parseInt(st.nextToken());
            for (int j = 0; j < Math.min(m, n - i); j++) {
                sellTable[i][j] = originPrice - Integer.parseInt(st.nextToken());
            }
        }

        long[] priceSum = new long[n + 1];
        Arrays.fill(priceSum, Long.MAX_VALUE);
        priceSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < Math.min(m, i); j++) {
                priceSum[i] = Math.min(priceSum[i], priceSum[i - 1 - j] + sellTable[i - 1 - j][j]);
            }
        }
        System.out.println(priceSum[n]);
    }
}