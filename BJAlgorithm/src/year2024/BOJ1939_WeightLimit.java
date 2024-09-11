package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1939_WeightLimit {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] path = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());
            path[A].add(new int[] { B, C });
            path[B].add(new int[] { A, C });
        }

        st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int[] next : path[start]) {
            pq.offer(next);
        }

        int answer = -1;
        boolean[] visited = new boolean[N];
        visited[start] = true;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (visited[cur[0]]) {
                continue;
            }
            if (cur[0] == end) {
                answer = cur[1];
                break;
            }

            visited[cur[0]] = true;
            for (int[] next : path[cur[0]]) {
                pq.offer(new int[] { next[0], Math.min(next[1], cur[1]) });
            }
        }
        System.out.println(answer);
    }
}