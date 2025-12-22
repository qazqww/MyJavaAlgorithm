package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3015_OasisReunion {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }

        long answer = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            else if (arr[i] < max) {
                answer++;
            }
        }

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Queue<Integer>> map = new HashMap<>();

        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] > stack.peek()) {
                long cnt = 1;
                Queue<Integer> queue = map.get(stack.peek());
                while (!queue.isEmpty()) {
                    queue.poll();
                    answer += cnt++;
                }
                map.remove(stack.pop());
            }

            if (stack.isEmpty() || arr[i] < stack.peek()) {
                int temp = stack.isEmpty() ? 0 : stack.peek();
                stack.push(arr[i]);
                map.put(arr[i], new LinkedList<>());
                if (arr[i] < temp) {
                    map.get(arr[i]).offer(i);
                }
            }
            else if (!stack.isEmpty() && arr[i] == stack.peek()) {
                map.get(stack.peek()).offer(i);
            }
        }

        while (!stack.isEmpty()) {
            int num = stack.pop();
            if (map.containsKey(num)) {
                long cnt = 1;
                Queue<Integer> queue = map.get(num);
                while (!queue.isEmpty()) {
                    queue.poll();
                    answer += cnt++;
                }
            }
        }

        System.out.println(answer);
    }
}


/*
2 4 1 2 2 5 1

1
5 -> 1 지움
5 2
5 2 1
4 -> 1 2 지움

[2]1 -> 4, 2 => 1, 1
[6]1 -> 5,  => 1, 0
[0]2 -> 4,  => 0, 1
[3]2 -> 4, 5 => 1, 2
[4]2 -> 4, 5 => 1, 1
[1]4 ->  , 5 => 0, 1
[5]5 ->  , => 0, 0

왼쪽으로 큰 수
오른쪽으로 큰 수, 자기보다 같은 수

2 4 1 2 2 5 1
                I
2 4 1 2 2  /  1
    I
2  /  1 2 2  /  1

n ->  ~ n보다 큰 가장 가까운 오른쪽

1 2 1 2 1


4 1 2 3 2 1 5
 */