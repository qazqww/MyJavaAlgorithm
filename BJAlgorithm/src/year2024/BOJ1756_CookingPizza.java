package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1756_CookingPizza {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] oven = new int[D];

        st = new StringTokenizer(in.readLine());
        int size = Integer.MAX_VALUE;
        for (int i = 0; i < D; i++) {
            int input = Integer.parseInt(st.nextToken());
            size = Math.min(size, input);
            oven[i] = size;
        }

        st = new StringTokenizer(in.readLine());
        int place = D - 1;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int pizza = Integer.parseInt(st.nextToken());
            for (; place >= 0; place--) {
                if (oven[place] >= pizza) {
                    answer = place--;
                    break;
                }
            }
            if (place < 0) {
                answer = -1;
                break;
            }
        }
        System.out.println(answer + 1);
    }
}