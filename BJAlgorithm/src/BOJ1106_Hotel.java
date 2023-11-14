import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1106_Hotel {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int target = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] dp = new int[2][target + 1];

        st = new StringTokenizer(in.readLine());
        int price = Integer.parseInt(st.nextToken());
        int customer = Integer.parseInt(st.nextToken());

        for (int j = 0; j < target + 1; j++) {
            dp[0][j] = (customer + j - 1) / customer * price;
            dp[1][j] = dp[0][j];
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            price = Integer.parseInt(st.nextToken());
            customer = Integer.parseInt(st.nextToken());

            for (int j = 1; j < target + 1; j++) {
                int newPrice = (customer + j - 1) / customer * price;
                dp[1][j] = Math.min(dp[0][j], newPrice);
                if (j >= customer) {
                    dp[1][j] = Math.min(dp[1][j], dp[1][j - customer] + price);
                }
            }
            System.arraycopy(dp[1], 0, dp[0], 0, dp[1].length);
        }
        System.out.println(dp[1][target]);
    }
}