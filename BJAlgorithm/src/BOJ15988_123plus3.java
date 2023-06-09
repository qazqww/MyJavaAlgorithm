import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15988_123plus3 {
    final static int MOD_NUM = 1_000_000_009;
    final static int MAX = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        int[] cnt = new int[MAX];
        cnt[1] = 1;
        cnt[2] = 2;
        cnt[3] = 4;

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(in.readLine());

            for (int i = 4; i <= n; i++) {
                if (cnt[i] != 0) {
                    continue;
                }
                cnt[i] = cnt[i - 3];
                cnt[i] %= MOD_NUM;
                cnt[i] += cnt[i - 2];
                cnt[i] %= MOD_NUM;
                cnt[i] += cnt[i - 1];
                cnt[i] %= MOD_NUM;
            }

            System.out.println(cnt[n]);
        }
    }
}