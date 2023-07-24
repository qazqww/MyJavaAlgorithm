import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2885_ChocoDining {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int low = 0;
        int high = 1;
        while (high < N) {
            high *= 2;
        }

        System.out.print(high + " ");

        if (high == N) {
            System.out.println(0);
            return;
        }

        int cnt = 0;
        while (low < high) {
            cnt++;
            int mid = (high + low) / 2;
            if (N == mid) {
                System.out.println(cnt);
                return;
            }
            else if (N < mid) {
                high = mid;
            }
            else {
                low = mid;
            }
        }

        System.out.println(cnt);
    }
}