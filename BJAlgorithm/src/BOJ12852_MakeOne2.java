// https://www.acmicpc.net/problem/12852

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ12852_MakeOne2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        Info[] infos = new Info[N + 1];
        for (int i = 0; i < N; i++) {
            infos[i] = new Info(100_000_000, null);
        }
        infos[N] = new Info(1, new int[] { N });

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);

        while (!queue.isEmpty()) {
            int num = queue.poll();

            if (num == 1) {
                break;
            }

            if (num % 3 == 0 && infos[num / 3].cnt > infos[num].cnt + 1) {
                int[] newArr = new int[infos[num].cnt + 1];
                System.arraycopy(infos[num].arr, 0, newArr, 0, infos[num].cnt);
                newArr[infos[num].cnt] = num / 3;
                infos[num / 3] = new Info(infos[num].cnt + 1, newArr);
                queue.offer(num / 3);
            }
            if (num % 2 == 0 && infos[num / 2].cnt > infos[num].cnt + 1) {
                int[] newArr = new int[infos[num].cnt + 1];
                System.arraycopy(infos[num].arr, 0, newArr, 0, infos[num].cnt);
                newArr[infos[num].cnt] = num / 2;
                infos[num / 2] = new Info(infos[num].cnt + 1, newArr);
                queue.offer(num / 2);
            }
            if (num > 1 && infos[num - 1].cnt > infos[num].cnt + 1) {
                int[] newArr = new int[infos[num].cnt + 1];
                System.arraycopy(infos[num].arr, 0, newArr, 0, infos[num].cnt);
                newArr[infos[num].cnt] = num - 1;
                infos[num - 1] = new Info(infos[num].cnt + 1, newArr);
                queue.offer(num - 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : infos[1].arr) {
            sb.append(num + " ");
        }
        sb.setLength(sb.length() - 1);

        System.out.println(infos[1].cnt - 1);
        System.out.println(sb);
    }

    static class Info {
        int cnt;
        int[] arr;

        public Info(int cnt, int[] arr) {
            this.cnt = cnt;
            this.arr = arr;
        }
    }
}