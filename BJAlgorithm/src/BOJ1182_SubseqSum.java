import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182_SubseqSum {
    static int N, S, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        answer = 0;
        arr = new int[N];

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        subset(0, 0, false);
        System.out.println(answer);
    }

    static void subset(int idx, int sum, boolean isValid) {
        if (idx == N) {
            if (sum == S && isValid) {
                answer++;
            }
            return;
        }
        subset(idx + 1, sum + arr[idx], true);
        subset(idx + 1, sum, isValid);
    }
}