package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11437_LCA {
    static int N;
    static int[][] treeInfo;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        treeInfo = new int[N][2]; // 부모, 깊이
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            treeInfo[i][0] = -1;
            treeInfo[i][1] = -1;
            adjList[i] = new ArrayList<>();
        }
        treeInfo[0][0] = -10;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int nodeA = Integer.parseInt(st.nextToken()) - 1;
            int nodeB = Integer.parseInt(st.nextToken()) - 1;
            adjList[nodeA].add(nodeB);
            adjList[nodeB].add(nodeA);
        }
        dfs(0, 0);

        int M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int nodeA = Integer.parseInt(st.nextToken()) - 1;
            int nodeB = Integer.parseInt(st.nextToken()) - 1;
            System.out.println(lca(nodeA, nodeB) + 1);
        }
    }

    static void dfs(int curNode, int depth) {
        treeInfo[curNode][1] = depth;
        for (int next : adjList[curNode]) {
            if (treeInfo[next][1] != -1) {
                continue;
            }
            treeInfo[next][0] = curNode;
            dfs(next, depth + 1);
        }
    }

    static int lca(int nodeA, int nodeB) {
        while (treeInfo[nodeA][1] != treeInfo[nodeB][1]) {
            if (treeInfo[nodeA][1] > treeInfo[nodeB][1]) {
                nodeA = treeInfo[nodeA][0];
            }
            else {
                nodeB = treeInfo[nodeB][0];
            }
        }
        while (nodeA != nodeB) {
            nodeA = treeInfo[nodeA][0];
            nodeB = treeInfo[nodeB][0];
        }
        return nodeA;
    }
}