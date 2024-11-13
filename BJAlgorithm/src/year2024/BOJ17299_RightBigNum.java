package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17299_RightBigNum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];
        Map<Integer, Integer> cnt = new HashMap<>();

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cnt.putIfAbsent(arr[i], 0);
            cnt.put(arr[i], cnt.get(arr[i]) + 1);
        }

        int[] answer = new int[N];
        Arrays.fill(answer, -1);
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && cnt.get(arr[i]) > stack.peek()[0]) {
                answer[stack.pop()[1]] = arr[i];
            }
            stack.push(new int[] { cnt.get(arr[i]), i });
        }

//        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
//        for (int i = 0; i < N; i++) {
//            while (!pq.isEmpty() && cnt.get(arr[i]) > pq.peek()[0]) {
//                int[] info = pq.poll();
//                answer[info[1]] = arr[i];
//            }
//            pq.offer(new int[] { cnt.get(arr[i]), i });
//        }

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}
/*
3 3 2 1 1 2 3
PQ<int[]> 낮은 수부터 { num, index }
1 1 2 3 3
 */