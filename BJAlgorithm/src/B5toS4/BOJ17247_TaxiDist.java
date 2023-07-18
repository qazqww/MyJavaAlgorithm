package B5toS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17247_TaxiDist {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] start = new int[] { -1, -1 };
        int[] end = new int[] { -1, -1 };

        loop: for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    if (start[0] == -1) {
                        start[0] = i;
                        start[1] = j;
                    }
                    else {
                        end[0] = i;
                        end[1] = j;
                        break loop;
                    }
                }
            }
        }
        System.out.println(Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]));
    }
}