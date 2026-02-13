package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647_CityDivisionPlan {

    static int[] parent;
    static int townCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        townCnt = N;
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.offer(new int[] { A, B, C });
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            if (townCnt <= 2) {
                break;
            }

            int[] road = pq.poll();
            if (union(road[0], road[1])) {
                answer += road[2];

            }
        }
        System.out.println(answer);
    }

    static int findRoot(int element) {
        return element != parent[element] ? parent[element] = findRoot(parent[element]) : element;
    }

    static boolean union(int a, int b) {
        int parentA = findRoot(a);
        int parentB = findRoot(b);
        if (parentA != parentB) {
            parent[parentA] = parentB;
            townCnt--;
            return true;
        }
        return false;
    }
}