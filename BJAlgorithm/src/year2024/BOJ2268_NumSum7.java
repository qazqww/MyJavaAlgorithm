package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2268_NumSum7 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        SegmentTree segmentTree = new SegmentTree(N);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 0:
                    sb.append(segmentTree.sum(1, Math.min(a, b), Math.max(a, b), 1, N)).append("\n");
                    break;
                case 1:
                    segmentTree.modify(1, a, b, 1, N);
                    break;
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class SegmentTree {
        long[] tree;
        int depth;
        int size;

        public SegmentTree(int N) {
            depth = (int) (Math.ceil(Math.log(N) / Math.log(2)) + 1);
            size = (int) Math.pow(2, depth);
            tree = new long[size];
        }

        long sum(int idx, int targetS, int targetE, int start, int end) {
            if (start >= targetS && end <= targetE) {
                return tree[idx];
            }
            if (end < targetS || start > targetE) {
                return 0;
            }
            int mid = (start + end) / 2;
            return sum(idx * 2, targetS, targetE, start, mid)
                    + sum(idx * 2 + 1, targetS, targetE, mid + 1, end);
        }

        long modify(int idx, int target, int newValue, int start, int end) {
            if (start == target && end == target) {
                long gap = newValue - tree[idx];
                tree[idx] = newValue;
                return gap;
            }
            if (target < start || target > end) {
                return 0;
            }
            int mid = (start + end) / 2;
            long gap = modify(idx * 2, target, newValue, start, mid)
                    + modify(idx * 2 + 1, target, newValue, mid + 1, end);
            tree[idx] += gap;
            return gap;
        }
    }
}