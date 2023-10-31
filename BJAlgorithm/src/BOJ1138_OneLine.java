import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1138_OneLine {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] arr = new int[N];

        for (int i = 1; i <= N; i++) {
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < N; j++) {
                if (arr[j] == 0) {
                    cnt--;
                    if (cnt == -1) {
                        arr[j] = i;
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i] + " ");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}