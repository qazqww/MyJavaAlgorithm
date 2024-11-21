package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3665_FinalRank {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());
        loop: for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(in.readLine());
            Map<Integer, ArrayList<Integer>> map = new HashMap<>();
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int cur = Integer.parseInt(st.nextToken());
                for (int key : map.keySet()) {
                    map.get(key).add(cur);
                }
                map.put(cur, new ArrayList<>());
            }

            int m = Integer.parseInt(in.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(in.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                boolean erased = false;
                for (int val : map.get(first)) {
                    if (val == second) {
                        map.get(first).remove((Object)val);
                        map.get(second).add(first);
                        erased = true;
                        break;
                    }
                }
                if (!erased) {
                    map.get(second).remove((Object)first);
                    map.get(first).add(second);
                }
            }
            int[] filled = new int[n];
            Arrays.fill(filled, -1);
            for (int key : map.keySet()) {
                filled[map.get(key).size()] = key;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = n - 1; i >= 0; i--) {
                sb.append(filled[i]).append(" ");
                if (filled[i] == -1) {
                    System.out.println("IMPOSSIBLE");
                    continue loop;
                }
            }
            sb.setLength(sb.length() - 1);
            System.out.println(sb);
        }
    }
}