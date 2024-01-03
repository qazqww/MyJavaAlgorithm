package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1326_HopHop {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] nums = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 1; i < N + 1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int answer = 0;

        if (start == end) {
            System.out.println(answer);
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            answer++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int now = queue.poll();

                if (nums[now] == 1) {
                    System.out.println(answer);
                    return;
                }

                int next = now - nums[now];
                while (next > 0) {
                    if (next == end) {
                        System.out.println(answer);
                        return;
                    }
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                    next -= nums[now];
                }
                next = now + nums[now];
                while (next < N + 1) {
                    if (next == end) {
                        System.out.println(answer);
                        return;
                    }
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                    next += nums[now];
                }
            }
        }
        System.out.println(-1);
    }
}