package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1202_GemThief {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> gems = new PriorityQueue<>((a, b) -> b[1] - a[1]); // 무게, 가치
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            gems.offer(new int[] { M, V });
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < K; i++) {
            int C = Integer.parseInt(in.readLine());
            if (map.containsKey(C)) {
                map.put(C, map.get(C) + 1);
            }
            else {
                map.put(C, 1);
            }
        }

        parent = new int[map.size()];
        Arrays.fill(parent, -1);
        int[][] bags = new int[map.size()][];
        int idx = 0;
        for (int key : map.keySet()) {
            bags[idx++] = new int[] { key, map.get(key) }; // 무게, 개수
        }
        Arrays.sort(bags, Comparator.comparingInt(a -> a[0]));

        long answer = 0;
        int filled = 0;
        while (filled < K && !gems.isEmpty()) {
            int[] gem = gems.poll();
            int start = 0;
            int end = bags.length - 1;
            int target = -1;
            while (start <= end) {
                int mid = (start + end) / 2;

                if (gem[0] <= bags[mid][0]) {
                    if (bags[mid][1] == 0) {
                        target = findRoot(mid) + 1;
                    }
                    else {
                        target = mid;
                    }
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                }
            }

            if (target == -1 || target >= bags.length) {
                continue;
            }

            answer += gem[1];
            filled++;
            bags[target][1]--;
            if (bags[target][1] == 0) {
                parent[target] = target;
                if (target - 1 >= 0 && bags[target - 1][1] == 0) {
                    union(target, target - 1);
                }
                if (target + 1 < bags.length && bags[target + 1][1] == 0) {
                    union(target, target + 1);
                }
            }
        }
        System.out.println(answer);
    }

    static void union(int a, int b) {
        int aRoot = findRoot(a);
        int bRoot = findRoot(b);
        if (a > b) {
            parent[b] = aRoot;
        }
        else {
            parent[a] = bRoot;
        }
    }

    static int findRoot(int num) {
        if (parent[num] == num) {
            return num;
        }
        return parent[num] = findRoot(parent[num]);
    }
}