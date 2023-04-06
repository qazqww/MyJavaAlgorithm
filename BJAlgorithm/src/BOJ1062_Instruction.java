import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1062_Instruction {
    static int N, K;
    static int cnt, max;
    static int[] nums;
    static int sample;
    static char[] chars = new char[26];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N];
        max = 0;

        sample = 0;
        sample |= (1 << 'a' - 'a'); // 0
        sample |= (1 << 'n' - 'a'); // 13
        sample |= (1 << 't' - 'a'); // 19
        sample |= (1 << 'i' - 'a'); // 8
        sample |= (1 << 'c' - 'a'); // 2

        Arrays.fill(chars, 'a');
        for (int i = 0; i < 26; i++) {
            chars[i] += i;
        }

        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            for (int j = 4; j < str.length() - 4; j++) {
                nums[i] |= (1 << str.charAt(j) - 'a');
            }
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        combi(0, 5);
        System.out.println(max);
    }

    static void combi(int n, int k) {
        if (k == K || n == 26) {
            cnt = 0;
            for (int i = 0; i < N; i++) {
                if ((nums[i] | sample) == sample) {
                    cnt++;
                }
            }
            max = Math.max(cnt, max);
            return;
        }
        combi(n + 1, k);
        if (n != 0 && n != 2 && n != 8 && n != 13 && n != 19) {
            sample |= (1 << chars[n] - 'a');
            combi(n + 1, k + 1);
            sample &= ~(1 << chars[n] - 'a');
        }
    }
}