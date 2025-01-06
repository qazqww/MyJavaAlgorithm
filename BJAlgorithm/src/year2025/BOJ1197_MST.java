package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1197_MST {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parents = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            parents[i] = i;
        }

        ArrayList<int[]> edgeList = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            edgeList.add(new int[] { start, end, value });
        }
        Collections.sort(edgeList, Comparator.comparingInt(a -> a[2]));

        int united = 1;
        int idx = 0;
        int result = 0;
        while (united < V) {
            int[] cur = edgeList.get(idx++);
            if (union(cur[0], cur[1])) {
                united++;
                result += cur[2];
            }
        }

        System.out.println(result);
    }

    static int findRoot(int num) {
        return parents[num] = parents[num] == num ? num : findRoot(parents[num]);
    }

    static boolean union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if (aRoot == bRoot) {
            return false;
        }

        parents[bRoot] = aRoot;
        return true;
    }
}