// https://www.acmicpc.net/problem/1922

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922_NetworkConnect {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int val = Integer.parseInt(st.nextToken());
            pq.offer(new int[] { a, b, val });
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if (union(now[0], now[1])) {
                sum += now[2];
            }
        }

        System.out.println(sum);
    }

    static int find(int num) {
        return parent[num] == num ? num : find(parent[num]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return false;

        parent[bRoot] = aRoot;
        return true;
    }
}