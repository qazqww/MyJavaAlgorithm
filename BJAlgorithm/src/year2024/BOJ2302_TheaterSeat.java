package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2302_TheaterSeat {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        int[] way = new int[N + 1];
        way[0] = 1;
        way[1] = 1;
        for (int i = 2; i < N + 1; i++) {
            way[i] = way[i - 2] + way[i - 1];
        }
        int answer = 1;
        int fixed = 0;
        for (int i = 0; i < M; i++) {
            int fix = Integer.parseInt(in.readLine());
            answer *= way[fix - fixed - 1];
            fixed = fix;
        }
        answer *= way[N - fixed];
        System.out.println(answer);
    }
}