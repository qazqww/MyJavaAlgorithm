package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14248_JumpJump {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] arr = new int[N];
        boolean[] visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int S = Integer.parseInt(in.readLine()) - 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(S);
        visited[S] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur + arr[cur] < N && !visited[cur + arr[cur]]) {
                queue.offer(cur + arr[cur]);
                visited[cur + arr[cur]] = true;
            }
            if (cur - arr[cur] >= 0 && !visited[cur - arr[cur]]) {
                queue.offer(cur - arr[cur]);
                visited[cur - arr[cur]] = true;
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}