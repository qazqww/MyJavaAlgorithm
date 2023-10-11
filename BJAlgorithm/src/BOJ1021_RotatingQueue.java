import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1021_RotatingQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] arr = new boolean[N];
        Arrays.fill(arr, true);
        int now = 0;
        int answer = 0;

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < M; i++) {
            int next = Integer.parseInt(st.nextToken()) - 1;
            int search = now;
            int ascFind = 0;
            int descFind = 0;

            // now로부터 뒤로 탐색
            while (search != next) {
                search = (search == N - 1) ? 0 : search + 1;
                if (!arr[search]) {
                    continue;
                }
                ascFind++;
            }
            // now로부터 앞으로 탐색
            search = now;
            while (search != next) {
                search = (search == 0) ? N - 1 : search - 1;
                if (!arr[search]) {
                    continue;
                }
                descFind++;
            }

            answer += Math.min(ascFind, descFind);
            arr[next] = false;
            while (i != M - 1 && !arr[next]) {
                next = (next == N - 1) ? 0 : next + 1;
            }
            now = next;
        }
        System.out.println(answer);
    }
}