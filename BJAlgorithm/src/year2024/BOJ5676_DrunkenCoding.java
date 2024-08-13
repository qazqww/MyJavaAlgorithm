package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5676_DrunkenCoding {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = in.readLine();
            if (str == null || str.isEmpty()) {
                break;
            }
            st = new StringTokenizer(str);
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[N + 1];
            st = new StringTokenizer(in.readLine());
            for (int i = 1; i <= N; i++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i] = num == 0 ? 0 : num / Math.abs(num);
            }

            SegmentTree tree = new SegmentTree(N);
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(in.readLine());
                char cmd = st.nextToken().charAt(0);
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());

                switch (cmd) {
                    case 'C':
                        num2 = num2 == 0 ? 0 : num2 / Math.abs(num2);
                        tree.changeNum(1, 1, N, num1, num2);
                        break;
                    case 'P':
                        int result = tree.getMultiple(1, 1, N, num1, num2);
                        switch (result) {
                            case 1:
                                sb.append('+');
                                break;
                            case 0:
                                sb.append('0');
                                break;
                            case -1:
                                sb.append('-');
                                break;
                        }
                        break;
                }
            }
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class SegmentTree {
        int[] tree;

        public SegmentTree(int len) {
            int depth = (int) (Math.ceil(Math.log(len) / Math.log(2)) + 1);
            int size = (int) Math.pow(2, depth);
            tree = new int[size];
            setup(1, 1, len);
        }

        int setup(int idx, int start, int end) {
            if (start == end) {
                tree[idx] = arr[start];
                return tree[idx];
            }
            int mid = (start + end) / 2;
            return tree[idx] = setup(idx * 2, start, mid) * setup(idx * 2 + 1, mid + 1, end);
        }

        int changeNum(int idx, int start, int end, int targetIdx, int targetNum) {
            if (targetIdx < start || targetIdx > end) {
                return tree[idx];
            }
            if (start == end) {
                return tree[idx] = targetNum;
            }
            int mid = (start + end) / 2;
            return tree[idx] = changeNum(idx * 2, start, mid, targetIdx, targetNum)
                    * changeNum(idx * 2 + 1, mid + 1, end, targetIdx, targetNum);
        }

        int getMultiple(int idx, int start, int end, int targetS, int targetE) {
            if (end < targetS || start > targetE) {
                return 1;
            }
            if (targetS <= start && end <= targetE) {
                return tree[idx];
            }
            int mid = (start + end) / 2;
            return getMultiple(idx * 2, start, mid, targetS, targetE)
                    * getMultiple(idx * 2 + 1, mid + 1, end, targetS, targetE);
        }
    }
}