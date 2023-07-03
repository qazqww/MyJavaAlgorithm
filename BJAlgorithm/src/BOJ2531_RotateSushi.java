import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ2531_RotateSushi {
    static Map<Integer, Integer> eat;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int menu = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int coupon = Integer.parseInt(st.nextToken());
        int max = 0;
        int[] onBelt = new int[N];

        for (int i = 0; i < N; i++) {
            onBelt[i] = Integer.parseInt(in.readLine());
        }

        eat = new HashMap<>();
        for (int i = 0; i < k; i++) {
            eatIn(onBelt[i]);
        }
        for (int i = k; i < N; i++) {
            eatIn(onBelt[i]);
            eatOut(onBelt[i - k]);
            int cnt = eat.size();
            if (!eat.containsKey(coupon)) {
                cnt++;
            }
            max = Math.max(max, cnt);
        }
        for (int i = N; i < N + k; i++) {
            eatIn(onBelt[i - N]);
            eatOut(onBelt[i - k]);
            int cnt = eat.size();
            if (!eat.containsKey(coupon)) {
                cnt++;
            }
            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }

    static void eatIn(int num) {
        if (eat.containsKey(num)) {
            eat.put(num, eat.get(num) + 1);
        }
        else {
            eat.put(num, 1);
        }
    }

    static void eatOut(int num) {
        if (eat.get(num) > 1) {
            eat.put(num, eat.get(num) - 1);
        }
        else {
            eat.remove(num);
        }
    }
}