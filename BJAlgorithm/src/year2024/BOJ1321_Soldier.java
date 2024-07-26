package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1321_Soldier {
    static int[] armySize;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        armySize = new int[N + 1];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++) {
            armySize[i] = Integer.parseInt(st.nextToken());
        }
        SegmentTree tree = new SegmentTree(N);

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case 1:
                    int target = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    tree.changeNum(1, 1, N, target, num);
                    break;
                case 2:
                    int soldier = Integer.parseInt(st.nextToken());
                    sb.append(tree.searchSum(1, 1, N, soldier)).append("\n");
                    break;
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class SegmentTree {
        int[] tree;
        int depth;
        int size;

        public SegmentTree(int num) {
            this.depth = (int) Math.ceil(Math.log(num) / Math.log(2)) + 1;
            this.size = (int) Math.pow(2, depth);
            this.tree = new int[size];
            makeTree(1, 1, num);
        }

        int makeTree(int idx, int start, int end) {
            if (start == end) {
                return tree[idx] = armySize[start];
            }
            int mid = (start + end) / 2;
            return tree[idx] = makeTree(idx * 2, start, mid)
                    + makeTree(idx * 2 + 1, mid + 1, end);
        }

        int searchSum(int idx, int start, int end, int target) {
            if (start == end) {
                return start;
            }

            int left = tree[idx * 2];
            int mid = (start + end) / 2;
            if (target > left) {
                return searchSum(idx * 2 + 1, mid + 1, end, target - left);
            }
            else {
                return searchSum(idx * 2, start, mid, target);
            }
        }

        void changeNum(int idx, int start, int end, int targetIdx, int num) {
            if (start > targetIdx || end < targetIdx) {
                return;
            }
            tree[idx] += num;
            if (start == end) {
                return;
            }

            int mid = (start + end) / 2;
            changeNum(idx * 2, start, mid, targetIdx, num);
            changeNum(idx * 2 + 1, mid + 1, end, targetIdx, num);
        }
    }
}
/*
1 => 1
2 => 2
3~4 => 3
5~8 => 4
9~16 => 5


1 4 3 5 2
1 1 3 5 2
1 1 3 7 2
1 5 3 7 2

1, 2, -3
2, 4, 2
3, 2, 4

시간, 인원누적합

 */