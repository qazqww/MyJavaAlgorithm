import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404_RGBStreet2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[][] dp = new int[9][2];

        StringTokenizer st = new StringTokenizer(in.readLine());
        int rStart = Integer.parseInt(st.nextToken());
        int gStart = Integer.parseInt(st.nextToken());
        int bStart = Integer.parseInt(st.nextToken());
        int rEnd = -1;
        int gEnd = -1;
        int bEnd = -1;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (i == 1) {
                dp[0][1] = 1_000_000_000;
                dp[1][1] = rStart + G;
                dp[2][1] = rStart + B;

                dp[3][1] = gStart + R;
                dp[4][1] = 1_000_000_000;
                dp[5][1] = gStart + B;

                dp[6][1] = bStart + R;
                dp[7][1] = bStart + G;
                dp[8][1] = 1_000_000_000;
            }
            else {
                dp[0][1] = Math.min(dp[1][0] + R, dp[2][0] + R);
                dp[1][1] = Math.min(dp[0][0] + G, dp[2][0] + G);
                dp[2][1] = Math.min(dp[0][0] + B, dp[1][0] + B);

                dp[3][1] = Math.min(dp[4][0] + R, dp[5][0] + R);
                dp[4][1] = Math.min(dp[3][0] + G, dp[5][0] + G);
                dp[5][1] = Math.min(dp[3][0] + B, dp[4][0] + B);

                dp[6][1] = Math.min(dp[7][0] + R, dp[8][0] + R);
                dp[7][1] = Math.min(dp[6][0] + G, dp[8][0] + G);
                dp[8][1] = Math.min(dp[6][0] + B, dp[7][0] + B);

                if (i == N - 1) {
                    rEnd = Math.min(dp[1][1], dp[2][1]);
                    gEnd = Math.min(dp[3][1], dp[5][1]);
                    bEnd = Math.min(dp[6][1], dp[7][1]);
                    break;
                }
            }

            for (int j = 0; j < 9; j++) {
                dp[j][0] = dp[j][1];
            }

        }

        System.out.println(Math.min(Math.min(rEnd, gEnd), bEnd));
    }
}