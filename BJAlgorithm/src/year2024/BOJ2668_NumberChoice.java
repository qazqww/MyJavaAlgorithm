package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class BOJ2668_NumberChoice {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] num = new int[N];
        boolean[] visited = new boolean[N];
        boolean[] checked = new boolean[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(in.readLine()) - 1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            int cur = i;
            Stack<Integer> stack = new Stack<>();
            while (!visited[cur]) {
                visited[cur] = true;
                stack.push(cur);
                cur = num[cur];
            }
            if (pq.contains(cur) || checked[cur]) {
                while (!stack.isEmpty()) {
                    checked[stack.pop()] = true;
                }
                continue;
            }
            while (stack.peek() != cur) {
                pq.offer(stack.pop());
            }
            pq.offer(stack.pop());
            while (!stack.isEmpty()) {
                checked[stack.pop()] = true;
            }
        }
        System.out.println(pq.size());
        while (!pq.isEmpty()) {
            System.out.println(pq.poll() + 1);
        }
    }
}
/*
1. visited[] 를 만듦
2. stack에 숫자를 담으며 가리키는 수를 탐색해나감
3. visited인 수가 나올 시, 그 수가 가리키는 수가 나올때까지 stack pop

1   2   3   4   5
4   3   4   2   5

3
1
3
4

 */