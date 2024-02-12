package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6118_HideAndSeek {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] answer = new int[3];
        answer[1] = -1;

        ArrayList<Integer>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            list[start].add(end);
            list[end].add(start);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            answer[0] = Integer.MAX_VALUE;
            answer[1]++;
            answer[2] = size;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                answer[0] = Math.min(answer[0], cur + 1);
                for (int next : list[cur]) {
                    if (visited[next]) {
                        continue;
                    }
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}