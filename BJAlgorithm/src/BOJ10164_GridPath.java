import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10164_GridPath {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        if (K == 0) {
            for (int i = 0; i < N; i++) {
                arr[i][0] = 1;
            }
            for (int i = 0; i < M; i++) {
                arr[0][i] = 1;
            }
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < M; j++) {
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                }
            }
        }
        else {
            int tempN = (K - 1) / M;
            int tempM = (K - 1) % M;
            for (int i = 0; i <= tempN; i++) {
                arr[i][0] = 1;
            }
            for (int i = 0; i <= tempM; i++) {
                arr[0][i] = 1;
            }
            for (int i = 1; i <= tempN; i++) {
                for (int j = 1; j <= tempM; j++) {
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                }
            }
            for (int i = tempN; i < N; i++) {
                for (int j = tempM; j < M; j++) {
                    if (i == tempN && j == tempM) {
                        continue;
                    }
                    int up = i > 0 ? arr[i - 1][j] : 0;
                    int left = j > 0 ? arr[i][j - 1] : 0;
                    arr[i][j] = up + left;
                }
            }
        }

        System.out.println(arr[N - 1][M - 1]);
    }
}