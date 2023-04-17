// https://www.acmicpc.net/problem/15666

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15666_NAndM12 {
    static int N, M, len;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        list = new ArrayList<>(set);
        Collections.sort(list);
        len = list.size();
        arr = new int[M];

        select(0, 0);

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        System.out.println(sb);
    }

    static void select(int idx, int start) {
        if (idx == M) {
            for (int num : arr) {
                sb.append(num + " ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
            return;
        }

        for (int i = start; i < len; i++) {
            arr[idx] = list.get(i);
            select(idx + 1, i);
        }
    }
}