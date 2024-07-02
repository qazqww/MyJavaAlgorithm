package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2887_PlanetTunnel {
    static int[][] parent;
    static int N;
    static boolean allConnected;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        parent = new int[N][2];
        int[][] xSort = new int[N][2];
        int[][] ySort = new int[N][2];
        int[][] zSort = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            xSort[i][0] = i;
            xSort[i][1] = Integer.parseInt(st.nextToken());
            ySort[i][0] = i;
            ySort[i][1] = Integer.parseInt(st.nextToken());
            zSort[i][0] = i;
            zSort[i][1] = Integer.parseInt(st.nextToken());
            parent[i][0] = i;
            parent[i][1] = 1;
        }
        Arrays.sort(xSort, Comparator.comparingInt(a -> a[1]));
        Arrays.sort(ySort, Comparator.comparingInt(a -> a[1]));
        Arrays.sort(zSort, Comparator.comparingInt(a -> a[1]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int i = 1; i < N; i++) {
            pq.offer(new int[] { xSort[i-1][0], xSort[i][0], Math.abs(xSort[i-1][1] - xSort[i][1]) });
            pq.offer(new int[] { ySort[i-1][0], ySort[i][0], Math.abs(ySort[i-1][1] - ySort[i][1]) });
            pq.offer(new int[] { zSort[i-1][0], zSort[i][0], Math.abs(zSort[i-1][1] - zSort[i][1]) });
        }

        int answer = 0;
        while (!allConnected) {
            int[] info = pq.poll();
            if (union(info[0], info[1])) {
                answer += info[2];
            }
        }

        System.out.println(answer);
    }

    static boolean union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if (aRoot == bRoot) {
            return false;
        }
        parent[aRoot][0] = bRoot;
        parent[bRoot][1] += parent[aRoot][1];
        if (parent[bRoot][1] == N) {
            allConnected = true;
        }
        return true;
    }

    static int findRoot(int num) {
        return num == parent[num][0] ? num : (parent[num][0] = findRoot(parent[num][0]));
    }
}
/*
1. 각 좌표를 정렬하여 각 행성에서 각 좌표별로 가까운 거리들을 뽑음
2. 뽑은 모든 거리를 (행성 1, 행성 2, 거리) 로 정렬
3. union find 해가면서 모든 행성이 합쳐질 때까지 반복
 */