package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ8980_Parcel {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(in.readLine());
        int[] delivering = new int[N + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { // 시작, 끝, 상자
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());
            pq.offer(new int[] { start, end, box });
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int canDeliver = info[2];
            for (int j = info[0]; j < info[1]; j++) {
                canDeliver = Math.min(canDeliver, C - delivering[j]);
            }
            if (canDeliver > 0) {
                for (int j = info[0]; j < info[1]; j++) {
                    delivering[j] += canDeliver;
                }
                answer += canDeliver;
            }
        }
        System.out.println(answer);
    }
}