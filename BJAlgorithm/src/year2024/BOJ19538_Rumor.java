package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19538_Rumor {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        ArrayList<Integer>[] adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            while (st.hasMoreTokens()) {
                int nearby = Integer.parseInt(st.nextToken());
                if (nearby == 0) {
                    break;
                }

                adjList[i].add(nearby - 1);
            }
        }

        int M = Integer.parseInt(in.readLine());
        st = new StringTokenizer(in.readLine());

        Queue<Integer> queue = new LinkedList<>();
        int[] rumorCnt = new int[N];
        int[] believedTime = new int[N];
        Arrays.fill(believedTime, -1);
        for (int i = 0; i < M; i++) {
            int startPoint = Integer.parseInt(st.nextToken()) - 1;
            queue.offer(startPoint);
            believedTime[startPoint] = 0;
            rumorCnt[startPoint]++;
        }

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int next : adjList[cur]) {
                    if (believedTime[next] < 0) {
                        rumorCnt[next]++;

                        if (rumorCnt[next] * 2 >= adjList[next].size()) {
                            believedTime[next] = time;
                            queue.offer(next);
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(believedTime[i]).append(" ");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}