import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11048_Moving {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N + M - 1; i++) {
            for (int j = 0; j <= i; j++) {
                if (j >= N || i - j >= M) {
                    continue;
                }

                int up = 0;
                int left = 0;

                if (j > 0) {
                    up = map[j - 1][i - j];
                }
                if (i - j > 0) {
                    left = map[j][i - j - 1];
                }

                map[j][i - j] += Math.max(up, left);
            }
        }
        System.out.println(map[N - 1][M - 1]);
    }
}