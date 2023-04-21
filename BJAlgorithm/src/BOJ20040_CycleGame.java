// https://www.acmicpc.net/problem/20040

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20040_CycleGame {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (!union(start, end)) {
                System.out.println(i + 1);
                return;
            }
        }

        System.out.println(0);
    }

    static int findRoot(int n) {
        if (parents[n] == n) {
            return n;
        }
        else {
            return parents[n] = findRoot(parents[n]);
        }
    }

    static boolean union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if (aRoot != bRoot) {
            parents[aRoot] = bRoot;
            return true;
        }
        else {
            return false;
        }
    }
}