import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ27968_Sasa4DCandy {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] candy = new long[M + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= M; i++) {
            candy[i] = candy[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            long needCandy = Long.parseLong(in.readLine());

            if (needCandy > candy[M]) {
                System.out.println("Go away!");
                continue;
            }

            long start = 1;
            long end = M;
            while (start < end) {
                int mid = (int)((start + end) / 2);
                if (candy[mid] < needCandy) {
                    start = mid + 1;
                }
                else if (candy[mid] > needCandy) {
                    end = mid;
                }
                else {
                    start = mid;
                    break;
                }
            }
            System.out.println(start);
        }
    }
}