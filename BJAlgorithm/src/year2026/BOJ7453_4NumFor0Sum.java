package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7453_4NumFor0Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(in.readLine());
        int arrCnt = 4;
        long[][] arr = new long[arrCnt][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < arrCnt; j++) {
                arr[j][i] = Long.parseLong(st.nextToken());
            }
        }

        long answer = 0L;
        long[] arrAB = new long[n * n];
        long[] arrCD = new long[n * n];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                arrAB[j * n + k] = arr[0][j] + arr[1][k];
                arrCD[j * n + k] = arr[2][j] + arr[3][k];
            }
        }

        Arrays.sort(arrAB);
        Arrays.sort(arrCD);

        int pointAB = 0;
        int pointCD = n * n - 1;

        while (pointAB <= n * n - 1 && pointCD >= 0) {
            if (arrAB[pointAB] + arrCD[pointCD] == 0) {
                long cntAB = 1L;
                int nowAB = pointAB;
                while (nowAB < n * n - 1 && arrAB[nowAB] == arrAB[nowAB + 1]) {
                    nowAB = nowAB + 1;
                    cntAB++;
                }

                long cntCD = 1L;
                int nowCD = pointCD;
                while (nowCD > 0 && arrCD[nowCD] == arrCD[nowCD - 1]) {
                    nowCD = nowCD - 1;
                    cntCD++;
                }

                answer += cntAB * cntCD;
                pointAB = nowAB;
                pointCD = nowCD;

                if (pointAB == n * n - 1) {
                    pointCD--;
                }
                else if (pointCD == 0) {
                    pointAB++;
                }
                else {
                    if (Math.abs(arrAB[pointAB] - arrAB[pointAB + 1]) > Math.abs(arrCD[pointCD] - arrCD[pointCD - 1])) {
                        pointCD--;
                    }
                    else {
                        pointAB++;
                    }
                }
            }
            else if (arrAB[pointAB] + arrCD[pointCD] < 0) {
                pointAB++;
            }
            else {
                pointCD--;
            }
        }
        System.out.println(answer);
    }
}