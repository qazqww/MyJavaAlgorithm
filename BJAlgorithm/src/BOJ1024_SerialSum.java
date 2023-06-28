import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1024_SerialSum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int basic = -1;
        for (int i = L; i <= 100; i++) {
            if (i % 2 == 0 && N % i == i / 2) {
                if (N / i + 1 - i / 2 < 0) {
                    continue;
                }
                basic = i;
                break;
            }
            else if (i % 2 == 1 && N % i == 0) {
                if (N / i - i / 2 + 0.5 < 0) {
                    continue;
                }
                basic = i;
                break;
            }
        }

        if (basic == -1) {
            System.out.println(-1);
        }
        else {
            StringBuilder sb = new StringBuilder();
            if (basic % 2 == 0) {
                for (int i = -basic / 2 + 1; i <= basic / 2; i++) {
                    sb.append(N / basic + i + " ");
                }
            }
            else {
                for (int i = -basic / 2; i < basic / 2 + 1; i++) {
                    sb.append(N / basic + i + " ");
                }
            }
            sb.setLength(sb.length() - 1);
            System.out.println(sb);
        }
    }
}