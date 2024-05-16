package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15900_TreeEscape {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st;
        ArrayList<Integer>[] adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adjList[a].add(b);
            adjList[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[N];
        visited[0] = true;
        int[] dist = new int[N];
        int total = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            boolean isLeaf = true;
            for (int next : adjList[cur]) {
                if (visited[next]) {
                    continue;
                }
                isLeaf = false;
                queue.offer(next);
                visited[next] = true;
                dist[next] = dist[cur] + 1;
            }
            if (isLeaf) {
                total += dist[cur];
            }
        }
        System.out.println(total % 2 == 0 ? "No" : "Yes");
    }
}