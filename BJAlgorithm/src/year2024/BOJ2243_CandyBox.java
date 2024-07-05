package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2243_CandyBox {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        int[][] commands = new int[N][3];
        int[] candy = new int[N];
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, Integer> mapper = new HashMap<>();
        Map<Integer, Integer> reverseMapper = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            commands[i][0] = A;
            commands[i][1] = B;
            if (A == 2) {
                set.add(B);
                commands[i][2] = Integer.parseInt(st.nextToken());
            }
        }

        for (int num : set) {
            pq.offer(num);
        }

        int idx = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            mapper.put(num, idx);
            reverseMapper.put(idx++, num);
        }

        for (int[] cmd : commands) {
            if (cmd[0] == 1) {
                idx = -1;
                int sum = 0;
                while (sum < cmd[1]) {
                    sum += candy[++idx];
                }
                candy[idx]--;
                System.out.println(reverseMapper.get(idx));
            }
            else {
                candy[mapper.get(cmd[1])] += cmd[2];
            }
        }
    }
}