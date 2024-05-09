package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2533_SNS {
    static ArrayList<Integer>[] adjList;
    static int[][] minCheck;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        adjList = new ArrayList[N];
        minCheck = new int[N][2]; // 0 : 자신을 체크, 1 : 자신을 미체크
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        int[] cnt = new int[N];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adjList[u].add(v);
            adjList[v].add(u);
            cnt[u]++;
            cnt[v]++;
        }

        search(0);
        System.out.println(Math.min(minCheck[0][0], minCheck[0][1]));
    }

    static void search(int no) {
        boolean isLeaf = true;
        visited[no] = true;

        // 하위 노드 탐색
        for (int next : adjList[no]) {
            if (!visited[next]) {
                isLeaf = false;
                visited[next] = true;
                search(next);
            }
        }

        // 본인이 말단 노드일 경우
        if (isLeaf) {
            minCheck[no][0] = 1;
            minCheck[no][1] = 0;
            return;
        }

        // 말단 노드의 최소값을 이용해 자신의 최소값 계산
        minCheck[no][0] = 1;
        for (int next : adjList[no]) {
            minCheck[no][0] += Math.min(minCheck[next][0], minCheck[next][1]);
            minCheck[no][1] += minCheck[next][0];
        }
    }
}

/*
1부터 탐색, dfs 식으로 전위 탐색하며 말단 노드를 찾음
말단 노드를 1로 처리
그 위 노드에선 말단 노드를 돌며 자신을 체크하는 경우와 아닌 경우를 계산하여 저장

자신 비체크 -> 바로 밑 모두 체크
자신 체크 -> 바로 밑은 적은거만 골라서
 */