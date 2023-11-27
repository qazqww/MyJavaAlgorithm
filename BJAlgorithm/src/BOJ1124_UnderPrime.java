import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1124_UnderPrime {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int A = Integer.parseInt(temp[0]);
        int B = Integer.parseInt(temp[1]);
        final int MAX_NUM = 100001;

        boolean[] isPrime = new boolean[MAX_NUM];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i < MAX_NUM; i++) {
            if (!isPrime[i]) {
                continue;
            }

            for (int curNum = i; curNum < MAX_NUM; curNum += i) {
                if (isPrime[curNum]) {
                    isPrime[curNum] = false;
                }
            }
        }

        System.out.println(isPrime[88888]);
        System.out.println(isPrime[99999]);
        System.out.println(isPrime[99997]);
        System.out.println(isPrime[11]);
    }
}
