package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14620_FlowerRoad {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        int[][] price = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        int[] dy = new int[] { 0, -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 0, 1, 0, -1 };
        for (int i = 0; i < (N-2)*(N-2) - 2; i++) {
            for (int j = i + 1; j < (N-2)*(N-2) - 1; j++) {
                for (int k = j + 1; k < (N-2)*(N-2); k++) {
                    if (!checkAdjacent(i, j) && !checkAdjacent(i, k) && !checkAdjacent(j, k)) {
                        int sum = 0;
                        for (int d = 0; d < 5; d++) {
                            sum += price[i / (N - 2) + 1 + dy[d]][i % (N - 2) + 1 + dx[d]];
                            sum += price[j / (N - 2) + 1 + dy[d]][j % (N - 2) + 1 + dx[d]];
                            sum += price[k / (N - 2) + 1 + dy[d]][k % (N - 2) + 1 + dx[d]];
                        }
                        answer = Math.min(answer, sum);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static boolean checkAdjacent(int num1, int num2) {
        if (num1 / (N - 2) == num2 / (N - 2)) {
            if (num2 - num1 == 1 || num2 - num1 == 2) {
                return true;
            }
        }
        else if (num1 / (N - 2) + 1 == num2 / (N - 2)) {
            if (num2 - num1 == (N - 2) - 1 ||num2 - num1 == (N - 2) || num2 - num1 == (N - 2) + 1) {
                return true;
            }
        }
        else if (num1 / (N - 2) + 2 == num2 / (N - 2)) {
            if (num2 - num1 == (N - 2) * 2) {
                return true;
            }
        }
        return false;
    }
}