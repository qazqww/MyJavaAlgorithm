package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1275_Coffeshop2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        SegmentTree segmentTree = new SegmentTree(N, nums);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min = Math.min(x, y);
            int max = Math.max(x, y);

            sb.append(segmentTree.getSum(1, min, max, 1, N)).append("\n");
            segmentTree.changeNum(1, a, b, 1, N);
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class SegmentTree {
        long[] arr;
        int[] nums;

        public SegmentTree(int len, int[] nums) {
            int depth = (int) Math.ceil(Math.log(len) / Math.log(2)) + 1;
            arr = new long[(int) Math.pow(2, depth)];
            this.nums = nums;
            init(1, 1, len);
        }

        long init(int idx, int start, int end) {
            if (start == end) {
                return arr[idx] = nums[start - 1];
            }
            int mid = (start + end) / 2;
            return arr[idx] = init(idx * 2, start, mid) + init(idx * 2 + 1, mid + 1, end);
        }

        long getSum(int idx, int left, int right, int start, int end) {
            if (end < left || start > right) {
                return 0;
            }
            if (start >= left && end <= right) {
                return arr[idx];
            }
            int mid = (start + end) / 2;
            return getSum(idx * 2, left, right, start, mid) +
                    getSum(idx * 2 + 1, left, right, mid + 1, end);
        }

        long changeNum(int idx, int target, int newNum, int start, int end) {
            if (start == target && start == end) {
                long oldNum = arr[idx];
                arr[idx] = newNum;
                return newNum - oldNum;
            }

            int mid = (start + end) / 2;
            if (start <= target && target <= end) {
                long diff = changeNum(idx * 2, target, newNum, start, mid)
                        + changeNum(idx * 2 + 1, target, newNum, mid + 1, end);
                arr[idx] += diff;
                return diff;
            }
            return 0;
        }
    }
}
/*
2147483647 -2147483648

5 2
2147483647 2147483647 2147483647 2147483647 2147483647
5 1 1 -2147483648
1 5 1 2147483647
 */