package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17472_BuildBridge2 {
    static final int[] DY = new int[] { -1, 0, 1, 0 };
    static final int[] DX = new int[] { 0, 1, 0, -1 };
    static int N, M, islandCnt;
    static int[][] map;
    static boolean[][] visited;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // 거리, 점A, 점B
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }

        findIsland();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                findBridge(i, j);
            }
        }

        int answer = 0;
        parent = new int[islandCnt];
        for (int i = 1; i < islandCnt; i++) {
            parent[i] = i;
        }
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (union(cur[1], cur[2])) {
                answer += cur[0];
            }
        }

        for (int i = 2; i < islandCnt; i++) {
            if (findRoot(1) != findRoot(i)) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(answer);
    }

    static void findBridge(int y, int x) {
        int no = map[y][x];
        for (int d = 0; d < 4; d++) {
            int ny = y + DY[d];
            int nx = x + DX[d];
            int cnt = 0;
            while (ny < N && ny >= 0 && nx < M && nx >= 0 && map[ny][nx] == 0) {
                ny += DY[d];
                nx += DX[d];
                cnt++;
            }
            if (ny >= N || ny < 0 || nx >= M || nx < 0 || cnt < 2 || no == map[ny][nx]) {
                continue;
            }
            pq.offer(new int[] { cnt, no, map[ny][nx] });
        }
    }

    static void findIsland() {
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    continue;
                }

                Queue<int[]> queue = new LinkedList<>();
                visited[i][j] = true;
                map[i][j] = cnt;
                queue.offer(new int[] { i, j });

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for (int d = 0; d < 4; d++) {
                        int ny = cur[0] + DY[d];
                        int nx = cur[1] + DX[d];

                        if (ny >= N || ny < 0 || nx >= M || nx < 0 || visited[ny][nx]) {
                            continue;
                        }

                        map[ny][nx] = cnt;
                        visited[ny][nx] = true;
                        queue.offer(new int[] { ny, nx });
                    }
                }
                cnt++;
            }
        }
        islandCnt = cnt;
    }

    static int findRoot(int num) {
        return num == parent[num] ? num : findRoot(parent[num]);
    }

    static boolean union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if (aRoot == bRoot) {
            return false;
        }
        parent[bRoot] = aRoot;
        return true;
    }
}
/*
5 5
1 0 0 0 1
1 0 0 0 1
0 0 0 0 0
1 0 0 0 1
1 0 0 0 1

4 8
0 0 0 0 0 0 1 1
1 0 0 1 1 0 0 1
1 1 1 1 0 0 0 0
1 1 0 0 0 1 1 0
 */