package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19941_HamburgerDistribute {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] map = in.readLine().toCharArray();
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (map[i] == 'P') {
                for (int j = i - K; j <= i + K; j++) {
                    if (j < 0 || j >= N) {
                        continue;
                    }
                    if (map[j] == 'H') {
                        map[j] = 'N';
                        answer++;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}