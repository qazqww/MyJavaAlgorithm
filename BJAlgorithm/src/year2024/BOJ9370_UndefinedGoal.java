package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ9370_UndefinedGoal {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            ArrayList<int[]>[] adjList = new ArrayList[n + 1]; // 도착지, 거리
            for (int i = 1; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }

            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                adjList[a].add(new int[] { b, d });
                adjList[b].add(new int[] { a, d });
            }

            PriorityQueue<Integer> answer = new PriorityQueue<>();
            for (int i = 0; i < t; i++) {
                st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { // 총 거리, 현재 위치, 거리 지나온 여부
                    if (a[0] != b[0]) {
                        return a[0] - b[0];
                    }
                    return b[2] - a[2];
                });
                pq.offer(new int[] { 0, s, 0 });
                boolean[] visited = new boolean[n + 1];

                while (!pq.isEmpty()) {
                    int[] cur = pq.poll();
                    if (visited[cur[1]]) {
                        continue;
                    }
                    visited[cur[1]] = true;
                    if (cur[1] == x) {
                        if (cur[2] > 0) {
                            answer.offer(x);
                        }
                        break;
                    }
                    for (int[] next : adjList[cur[1]]) {
                        if (visited[next[0]]) {
                            continue;
                        }
                        int smelt = (g == cur[1] && h == next[0] || g == next[0] && h == cur[1]) ? 1 : 0;
                        pq.offer(new int[] { cur[0] + next[1], next[0], cur[2] + smelt });
                    }
                }
            }

            while (!answer.isEmpty()) {
                sb.append(answer.poll()).append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}