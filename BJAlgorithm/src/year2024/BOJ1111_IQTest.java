package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1111_IQTest {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N <= 2) {
            if (N == 2 && arr[0] == arr[1]) {
                System.out.println(arr[0]);
            }
            else {
                System.out.println("A");
            }
            return;
        }

        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[0]) {
                if (i == N - 1) {
                    System.out.println(arr[0]);
                    return;
                }
            }
            else {
                break;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = -30000; i <= 30000; i++) {
            queue.offer(i);
        }

        for (int i = N - 1; i > 0; i--) {
            int size = queue.size();

            if (arr[i - 1] == 0) {
                int plus = arr[i];
                for (int j = 0; j < size; j++) {
                    int num = queue.poll();
                    if (num == plus) {
                        queue.offer(num);
                    }
                    else {
                        map.remove(num);
                    }
                }
                continue;
            }

            for (int j = 0; j < size; j++) {
                int num = queue.poll();
                int a = arr[i] - num;
                int b = arr[i - 1];
                if (a % b == 0) {
                    if (!map.containsKey(num)) {
                        map.put(num, a / b);
                        queue.offer(num);
                    }
                    else if (map.get(num) == a / b) {
                        queue.offer(num);
                    }
                    else {
                        map.remove(num);
                    }
                }
                else {
                    map.remove(num);
                }
            }
        }

        if (queue.size() > 1) {
            System.out.println("A");
            return;
        }
        else if (queue.isEmpty()) {
            System.out.println("B");
            return;
        }

        int num = queue.poll();
        System.out.println(arr[N - 1] * map.get(num) + num);
    }
}