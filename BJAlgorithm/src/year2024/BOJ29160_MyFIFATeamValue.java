package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ29160_MyFIFATeamValue {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer>[] pq = new PriorityQueue[11];
        for (int i = 0; i < 11; i++) {
            pq[i] = new PriorityQueue<>((a, b) -> b - a);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int pos = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());
            pq[pos].offer(value);
        }
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 11; j++) {
                if (!pq[j].isEmpty()) {
                    int num = pq[j].poll();
                    pq[j].offer(num - 1);
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < 11; i++) {
            if (!pq[i].isEmpty()) {
                answer += pq[i].poll();
            }
        }
        System.out.println(answer);
    }
}