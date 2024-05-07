package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1781_CupRamen {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st;
        ArrayList<Integer>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int deadline = Integer.parseInt(st.nextToken()) - 1;
            int reward = Integer.parseInt(st.nextToken());
            list[deadline].add(reward);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int answer = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int num : list[i]) {
                pq.offer(num);
            }
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }
        System.out.println(answer);
    }
}