package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ13334_Railway {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < n; i++) {
            String[] temp = in.readLine().split(" ");
            int min = Math.min(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            int max = Math.max(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            pq.offer(new int[] { min, max });
        }
        int d = Integer.parseInt(in.readLine());

        PriorityQueue<int[]> include = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int answer = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            include.offer(cur);
            while (!include.isEmpty() && include.peek()[0] < cur[1] - d) {
                include.poll();
            }
            answer = Math.max(answer, include.size());
        }
        System.out.println(answer);
    }
}