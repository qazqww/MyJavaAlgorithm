package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14267_CompanyCulture {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] childList = new ArrayList[n];
        int[] given = new int[n];
        int[] taken = new int[n];
        for (int i = 0; i < n; i++) {
            childList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(in.readLine());
        st.nextToken();
        for (int i = 1; i < n; i++) {
            childList[Integer.parseInt(st.nextToken()) - 1].add(i);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int praise = Integer.parseInt(st.nextToken());
            taken[idx] += praise;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int parent = queue.poll();
            for (int child : childList[parent]) {
                taken[child] += taken[parent];
                queue.offer(child);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int num : taken) {
            sb.append(num + " ");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}