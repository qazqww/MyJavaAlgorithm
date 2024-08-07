package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2357_MinAndMax {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }
        SegmentTree tree = new SegmentTree(N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int[] result = tree.getValue(1, 1, N, start, end);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class SegmentTree {
        int[][] tree;
        int depth;
        int size;

        public SegmentTree(int len) {
            depth = (int) Math.ceil(Math.log(len) / Math.log(2)) + 1;
            size = (int) Math.pow(2, depth);
            tree = new int[size][2];
            setup(1, 1, len);
        }

        int[] setup(int idx, int start, int end) {
            if (start == end) {
                tree[idx][0] = tree[idx][1] = arr[start];
                return new int[] { tree[idx][0], tree[idx][1] };
            }
            int mid = (start + end) / 2;
            int[] infoLeft = setup(idx * 2, start, mid);
            int[] infoRight = setup(idx * 2 + 1, mid + 1, end);
            tree[idx][0] = Math.min(infoLeft[0], infoRight[0]);
            tree[idx][1] = Math.max(infoLeft[1], infoRight[1]);
            return tree[idx];
        }

        int[] getValue(int idx, int start, int end, int targetS, int targetE) {
            if (end < targetS || start > targetE) {
                return new int[] { Integer.MAX_VALUE, -1 };
            }
            if (start >= targetS && end <= targetE) {
                return tree[idx];
            }
            int mid = (start + end) / 2;
            int[] infoLeft = getValue(idx * 2, start, mid, targetS, targetE);
            int[] infoRight = getValue(idx * 2 + 1, mid + 1, end, targetS, targetE);
            return new int[] { Math.min(infoLeft[0], infoRight[0]), Math.max(infoLeft[1], infoRight[1]) };
        }
    }
}