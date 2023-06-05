import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2004_CombiZeroCnt {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str = in.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int nTwo = 0;
        int nFive = 0;
        int mTwo1 = 0;
        int mFive1 = 0;
        int mTwo2 = 0;
        int mFive2 = 0;

        long two = 2;
        while (two <= n) {
            nTwo += n / two;
            two *= 2;
        }
        long five = 5;
        while (five <= n) {
            nFive += n / five;
            five *= 5;
        }

        two = 2;
        while (two <= n - m) {
            mTwo1 += (n - m) / two;
            two *= 2;
        }
        five = 5;
        while (five <= n - m) {
            mFive1 += (n - m) / five;
            five *= 5;
        }

        two = 2;
        while (two <= m) {
            mTwo2 += m / two;
            two *= 2;
        }
        five = 5;
        while (five <= m) {
            mFive2 += m / five;
            five *= 5;
        }

        nTwo = nTwo - mTwo1 - mTwo2;
        nFive = nFive - mFive1 - mFive2;
        System.out.println(nTwo < nFive ? nTwo : nFive);
    }
}