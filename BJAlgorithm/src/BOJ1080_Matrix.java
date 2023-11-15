import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1080_Matrix {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] curArr = new int[N][M];
        int[][] targetArr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String temp = in.readLine();
            for (int j = 0; j < M; j++) {
                curArr[i][j] = temp.charAt(j) - '0';
            }
        }
        for (int i = 0; i < N; i++) {
            String temp = in.readLine();
            for (int j = 0; j < M; j++) {
                targetArr[i][j] = temp.charAt(j) - '0';
            }
        }

        int answer = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (curArr[i][j] % 2 != targetArr[i][j]) {
                    answer++;
                    for (int n = i; n < i + 3; n++) {
                        for (int m = j; m < j + 3; m++) {
                            curArr[n][m]++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (curArr[i][j] % 2 != targetArr[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(answer);
    }
}