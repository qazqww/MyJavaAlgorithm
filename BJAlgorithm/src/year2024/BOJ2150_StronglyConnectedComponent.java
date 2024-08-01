package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2150_StronglyConnectedComponent {
    static Stack<Integer> stack = new Stack<>();
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static boolean[] completed;
    static int[][] info;
    static int order = 0;
    static PriorityQueue<ArrayList<Integer>> scc = new PriorityQueue<>(
            Comparator.comparingInt(a -> a.get(0))
    );

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        completed = new boolean[V + 1];
        info = new int[V + 1][2]; // 부모, 고유
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
        }

        for (int i = 1; i <= V; i++) {
            if (!completed[i]) {
                dfs(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(scc.size()).append("\n");
        while (!scc.isEmpty()) {
            ArrayList<Integer> list = scc.poll();
            for (int num : list) {
                sb.append(num).append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static int dfs(int idx) {
        stack.push(idx);
        visited[idx] = true;
        info[idx][0] = idx;
        info[idx][1] = order++;

//        System.out.println(idx + " : " + adjList[idx]);
        for (int next : adjList[idx]) {
            if (completed[next]) {
                continue;
            }
            int parent = info[idx][0];
            int nextParent;
            if (visited[next]) {
                nextParent = info[next][0];
            }
            else {
                nextParent = dfs(next);
            }
            if (info[parent][1] > info[nextParent][1]) {
                info[idx][0] = nextParent;
            }
        }
        if (info[idx][0] == idx) {
            ArrayList<Integer> result = new ArrayList<>();
            while (!stack.isEmpty() && stack.peek() != idx) {
                completed[stack.peek()] = true;
                result.add(stack.pop());
            }
            completed[stack.peek()] = true;
            result.add(stack.pop());
            Collections.sort(result);
            result.add(-1);
            scc.offer(result);
        }
        return info[idx][0];
    }
}
/*
a b e(->a) f g h(->h)X (->f)X
이미 방문한걸 방문했으면 그것이 방문을 끝냈을때 pop하여 같은 집단으로

13 17
1 2
2 3
3 1
9 6
6 8
8 5
5 7
7 6
10 5
1 5
5 13
13 4
4 3
11 13
13 12
12 11
10 11

8 14
1 2
2 5
5 1
2 3
3 4
4 3
4 8
8 8
3 7
7 8
7 6
6 7
2 6
5 6
 */