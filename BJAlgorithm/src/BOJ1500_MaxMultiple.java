import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1500_MaxMultiple {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        long S = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long avg = S / K;
        long remain = S % K;

        long answer = 1;
        for (int i = 0; i < K - remain; i++) {
            answer *= avg;
        }
        for (int i = 0; i < remain; i++) {
            answer *= avg + 1;
        }
        System.out.println(answer);
    }
}