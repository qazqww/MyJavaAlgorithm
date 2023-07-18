package B5toS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1049_GuitarString {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = 0;
        int packMin = Integer.MAX_VALUE;
        int oneMin = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int pack = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());

            packMin = Math.min(packMin, pack);
            oneMin = Math.min(oneMin, one);
        }

        if (oneMin * 6 < packMin) {
            answer = N * oneMin;
        }
        else {
            answer += N / 6 * packMin;
            if ((N - N/6 * 6) * oneMin < packMin) {
                answer += (N - N/6 * 6) * oneMin;
            }
            else {
                answer += packMin;
            }
        }

        System.out.println(answer);
    }
}