package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2109_CircuitLecture {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(in.readLine());
        PriorityQueue<int[]> info = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> reward = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            info.offer(new int[] {
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
            });
        }

        int answer = 0;
        int curDay = info.isEmpty() ? -1 : info.peek()[1];
        while (curDay > 0) {
            while (!info.isEmpty() && info.peek()[1] >= curDay) {
                reward.offer(info.poll()[0]);
            }
            if (!reward.isEmpty()) {
                answer += reward.poll();
            }
            curDay--;
        }
        System.out.println(answer);
    }
}
/*
0

6
10 3
15 3
2 2
8 2
1 1
5 1

7
2 1
19 1
3 2
11 3
12 3
10 4
15 4

1   2   3   4   5   10  20
2   100 10          5   50
20  8

1   2   3
1   2   10
5   8   15

1   2   3   4
2   3   11  10
19      12  15
 */