package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1124_Underprime {
    static boolean[] isPrime;
    static ArrayList<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        setup(B);

        int answer = 0;
        for (int i = A; i <= B; i++) {
            int num = i;
            int idx = 0;
            int cnt = 1;

            while (!isPrime[num]) {
                if (num % primeList.get(idx) == 0) {
                    num /= primeList.get(idx);
                    cnt++;
                }
                else {
                    idx++;
                }
            }

            if (isPrime[cnt]) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static void setup(int limit) {
        isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * 2; j < isPrime.length; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }
    }
}
