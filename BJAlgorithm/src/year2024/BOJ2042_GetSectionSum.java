package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2042_GetSectionSum {
    static int N;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(in.readLine());
        }
        SegmentTree tree = new SegmentTree(N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                tree.changeNum(1, 1, N, b, c);
            }
            else if (a == 2) {
                sb.append(tree.getSum(1, 1, N, b, (int) c)).append("\n");
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class SegmentTree {
        long[] tree;
        int depth;
        int size;

        public SegmentTree(int size) {
            depth = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
            this.size = (int) Math.pow(2, depth);
            tree = new long[this.size];
            makeTree(1, 1, N);
        }

        void print() {
            System.out.println(Arrays.toString(tree));
        }

        long makeTree(int idx, int start, int end) {
            if (start == end) {
                tree[idx] = arr[start];
                return tree[idx];
            }
            int mid = (start + end) / 2;
            return tree[idx] = makeTree(idx * 2, start, mid)
                    + makeTree(idx * 2 + 1, mid + 1, end);
        }

        long getSum(int idx, int start, int end, int targetStart, int targetEnd) {
            if (targetStart <= start && targetEnd >= end) {
                return tree[idx];
            }
            else if (targetStart > end || targetEnd < start) {
                return 0;
            }
            int mid = (start + end) / 2;
            return getSum(idx * 2, start, mid, targetStart, targetEnd)
                    + getSum(idx * 2 + 1, mid + 1, end, targetStart, targetEnd);
        }

        long changeNum(int idx, int start, int end, int targetIdx, long newNum) {
            if (targetIdx < start || targetIdx > end) {
                return 0;
            }
            if (start == end) {
                long originNum = tree[idx];
                tree[idx] = newNum;
                return newNum - originNum;
            }
            int mid = (start + end) / 2;
            long gap = changeNum(idx * 2, start, mid, targetIdx, newNum)
                    + changeNum(idx * 2 + 1, mid + 1, end, targetIdx, newNum);
            tree[idx] += gap;
            return gap;
        }
    }
}