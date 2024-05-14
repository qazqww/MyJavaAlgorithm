package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2457_PrincessGarden {
    static final int[] DAYS = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public static void main(String[] args) throws IOException {
        int start = 0, end = 0;
        start = DAYS[0] + DAYS[1] + 1;
        for (int i = 0; i < 11; i++) {
            end += DAYS[i];
        }
        end += 1;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st;
        int[][] flower = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = Integer.parseInt(st.nextToken());

            int sNum = 0, eNum = 0;
            for (int j = 0; j < sMonth - 1; j++) {
                sNum += DAYS[j];
            }
            sNum += sDay;
            for (int j = 0; j < eMonth - 1; j++) {
                eNum += DAYS[j];
            }
            eNum += eDay;
            flower[i][0] = sNum < start ? start : sNum;
            flower[i][1] = eNum > end ? end : eNum;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int bloom = start;
        int cnt = 0;
        while (bloom < end) {
            for (int i = 0; i < N; i++) {
                if (flower[i][0] <= bloom && flower[i][1] > bloom) {
                    pq.offer(flower[i]);
                }
            }
            if (pq.isEmpty()) {
                System.out.println(0);
                return;
            }
            int[] selected = pq.poll();
            bloom = selected[1];
            cnt++;
        }
        System.out.println(cnt);
    }
}