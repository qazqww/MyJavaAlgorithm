package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14942_Ant {
    static int n;
    static int[] energy;
    static int[][][] tree;
    static ArrayList<int[]>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(in.readLine());
        energy = new int[n + 1];
        tree = new int[n + 1][][];
        tree[1] = new int[1][1];
        tree[1][0][0] = 1;
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            energy[i] = Integer.parseInt(in.readLine());
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adjList[start].add(new int[] { end, dist });
            adjList[end].add(new int[] { start, dist });
        }
        traversalTree();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(searchGoal(i)).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);

//        for (int i = 2; i < tree.length; i++) {
//            for (int j = 0; j < tree[i].length; j++) {
//                System.out.println(i + "/" + j + " => " + tree[i][j][0] + " " + tree[i][j][1]);
//            }
//        }
    }

    static int searchGoal(int idx) {
        if (idx == 1) {
            return 1;
        }

        int remainEnergy = energy[idx];
        int searchIdx = idx;
        int nextIdx = tree[searchIdx].length - 1;
        while (searchIdx != 1) {
            if (tree[searchIdx][nextIdx][1] > remainEnergy) {
                nextIdx--;
            }
            if (nextIdx < 0) {
                return searchIdx;
            }
            if (tree[searchIdx][nextIdx][1] <= remainEnergy) {
                remainEnergy -= tree[searchIdx][nextIdx][1];
                searchIdx = tree[searchIdx][nextIdx][0];
                nextIdx = tree[searchIdx].length - 1;
            }
        }
        return tree[searchIdx][nextIdx][0];
    }

    static void traversalTree() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(1);
        visited[0] = visited[1] = true;

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int[] nextInfo : adjList[cur]) {
                    int next = nextInfo[0];
                    if (visited[next]) {
                        continue;
                    }

                    tree[next] = new int[(int) (Math.log(depth) / Math.log(2)) + 1][2];
                    tree[next][0] = new int[] { cur, nextInfo[1] };

                    // tree[next][j - 1][0] => 이전꺼
                    for (int j = 1; j < tree[next].length; j++) {
                        tree[next][j] = new int[] {
                                tree[tree[next][j - 1][0]][j - 1][0],
                                tree[next][j - 1][1] + tree[tree[next][j - 1][0]][j - 1][1]
                            };
                    }
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
}
/*
 node[i][0] => 1번쨰 위, 그 사이 값의 합
 node[i][1] => 2번쨰 위
 node[i][2] => 4번쨰 위
 node[i][3] => 8번쨰 위

9
1
11
19
100
100
30
50
50
100
1 2 10
2 3 10
3 4 10
4 5 10
5 6 10
6 7 10
7 8 10
8 9 10

 */