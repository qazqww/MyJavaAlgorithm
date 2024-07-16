package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1471_CandyRotate {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        int[] len = new int[N + 1];
        Arrays.fill(len, -1);
        Set<Integer> set;
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i < N + 1; i++) {
            if (len[i] != -1) {
                continue;
            }

            set = new HashSet<>();
            stack.push(i);
            set.add(i);
            len[i] = 0; // 방문한 칸임을 임시 표시
            int next = getDist(i);

            while (len[next] == -1) { // 방문하지 않은 칸들을 탐색
                stack.push(next);
                set.add(next);
                len[next] = 0;
                next = getDist(next);
            }

            // 탐색중인 칸으로 재방문하는 경우
            if (set.contains(next)) {
                int cnt = 0;
                Stack<Integer> circuitNum = new Stack<>();
                while (stack.peek() != next) {
                    circuitNum.push(stack.pop());
                    cnt++;
                }
                circuitNum.push(stack.pop());
                cnt++;

                while (!circuitNum.isEmpty()) {
                    len[circuitNum.pop()] = cnt;
                }

                while (!stack.isEmpty()) {
                    len[stack.pop()] = ++cnt;
                }
            }
            // 탐색을 마친 칸으로 재방문하는 경우
            else {
                int cnt = len[next] + 1;
                while (!stack.isEmpty()) {
                    len[stack.pop()] = cnt++;
                }
            }
        }

        int max = -1;
        for (int num : len) {
            max = Math.max(max, num);
        }
        System.out.println(max);
    }

    static int getDist(int num) {
        int temp = num;
        int sum = 0;
        while (temp > 0) {
            sum += temp % 10;
            temp /= 10;
        }
        int result = num + sum;
        if (result > N) {
            return result - N;
        }
        return result;
    }
}
/*


1 2
1/3 2 4
2 4 3 2 4

1 2 4 3
1=4
2->1
4->1
3->1

6 -> ... -> 1 : arr[1] + 길이

 */