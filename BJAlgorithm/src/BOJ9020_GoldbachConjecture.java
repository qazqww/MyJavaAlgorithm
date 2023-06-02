import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9020_GoldbachConjecture {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        boolean[] isPrime = new boolean[10001];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i <= 10000; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i + i; j <= 10000; j += i) {
                isPrime[j] = false;
            }
        }

        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(in.readLine());
            int half = num / 2;
            if (isPrime[half]) {
                System.out.println(String.format("%d %d", half, half));
                continue;
            }
            for (int i = 1; i < half; i++) {
                if (isPrime[half - i] && isPrime[half + i]) {
                    System.out.println(String.format("%d %d", half - i, half + i));
                    break;
                }
            }
        }
    }
}