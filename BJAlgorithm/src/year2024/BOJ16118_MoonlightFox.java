package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16118_MoonlightFox {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adjList[a].add(new int[] { b, d*4 });
            adjList[b].add(new int[] { a, d*4 });
        }

        long[] fox = new long[N + 1];
        long[][] wolf = new long[2][N + 1]; // 느리게 도착, 빠르게 도착
        Arrays.fill(fox, Long.MAX_VALUE);
        Arrays.fill(wolf[0], Long.MAX_VALUE);
        Arrays.fill(wolf[1], Long.MAX_VALUE);
        fox[1] = wolf[0][1] = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        pq.offer(new Info(1, 0, 4));
        while (!pq.isEmpty()) {
            Info cur = pq.poll(); // 현 위치, 총 거리, 속도
            int idx = cur.speed / 4;
            if (wolf[1 - idx][cur.curIdx] < cur.dist) {
                continue;
            }
            for (int[] next : adjList[cur.curIdx]) {
                long newDist = cur.dist + next[1] / cur.speed;
                if (wolf[idx][next[0]] > newDist) {
                    wolf[idx][next[0]] = newDist;
                    pq.offer(new Info(next[0], newDist, 4 / cur.speed));
                }
            }
        }

        pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        pq.offer(new Info(1, 0, 2));
        while (!pq.isEmpty()) {
            Info cur = pq.poll(); // 현 위치, 총 거리
            if (fox[cur.curIdx] < cur.dist) {
                continue;
            }
            for (int[] next : adjList[cur.curIdx]) {
                long newDist = cur.dist + next[1] / cur.speed;
                if (fox[next[0]] > newDist) {
                    fox[next[0]] = newDist;
                    pq.offer(new Info(next[0], newDist, cur.speed));
                }
            }
        }

        int answer = 0;
        for (int i = 2; i <= N; i++) {
            if (fox[i] < wolf[0][i] && fox[i] < wolf[1][i]) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static class Info {
        int curIdx;
        long dist;
        int speed;

        public Info(int curIdx, long dist, int speed) {
            this.curIdx = curIdx;
            this.dist = dist;
            this.speed = speed;
        }
    }
}