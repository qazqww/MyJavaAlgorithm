package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ26013_ChaoticConstruction {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        SegmentTree tree = new SegmentTree(n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            char event = st.nextToken().charAt(0);
            if (event == '?') {
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int max = Math.max(start, end);
                int min = Math.min(start, end);

                // min ~ max
                long front = tree.getSum(1, min, max, 1, n);
                long rear = tree.getSum(1, 1, min, 1, n) + tree.getSum(1, max, n, 1, n);
                if (front > 0 || rear > 0) {
                    sb.append("possible\n");
                }
                else {
                    sb.append("impossible\n");
                }
            }
            else {
                int target = Integer.parseInt(st.nextToken());
                if (event == '+') {
                    tree.changeValue(1, target,1, n, 1_000_000);
                }
                else {
                    tree.changeValue(1, target,1, n, -1_000_000);
                }
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class SegmentTree {
        long[] tree;
        int depth;
        int size;

        public SegmentTree(int element) {
            depth = (int) Math.ceil(Math.log(element) / Math.log(2)) + 1;
            size = (int) Math.pow(2, depth);
            tree = new long[size];
            makeTree(1, 1, element);
        }

        long makeTree(int idx, int start, int end) {
            if (start == end) {
                return tree[idx] = 1;
            }
            else {
                int mid = (start + end) / 2;
                return tree[idx] = makeTree(idx * 2, start, mid) + makeTree(idx * 2 + 1, mid + 1, end);
            }
        }

        long getSum(int idx, int left, int right, int start, int end) {
            if (right < start || left > end) {
                return 0;
            }
            if (left <= start && right >= end) {
                return tree[idx];
            }
            int mid = (start + end) / 2;
            long leftSide = getSum(idx * 2, left, right, start, mid);
            long rightSide = getSum(idx * 2 + 1, left, right, mid + 1, end);
            return leftSide + rightSide;
        }

        void changeValue(int idx, int target, int start, int end, int val) {
            if (target < start || target > end) {
                return;
            }
            tree[idx] += val;
            if (start == end) {
                return;
            }
            int mid = (start + end) / 2;
            changeValue(idx * 2, target, start, mid, val);
            changeValue(idx * 2 + 1, target, mid + 1, end, val);
        }
    }
}
/*
2 12
? 1 2
? 2 1
- 2
? 1 2
? 2 1
+ 2
- 1
? 1 2
? 2 1
+ 1
? 1 2
? 2 1


10 14
? 1 5
- 2
- 8
? 9 2
? 9 8
? 9 7
? 6 7
? 3 7
? 1 9
? 9 1
+ 8
? 10 3
? 1 9
? 9 1
 */