import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5567_Wedding {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        int M = Integer.parseInt(in.readLine());
        ArrayList<Integer>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            list[a].add(b);
            list[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[N];
        visited[0] = true;

        int cnt = 0;
        while (!queue.isEmpty() && cnt < 2) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();
                for (int friend : list[num]) {
                    if (visited[friend]) {
                        continue;
                    }
                    queue.offer(friend);
                    visited[friend] = true;
                }
            }
            cnt++;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                answer++;
            }
        }
        System.out.println(answer - 1);
    }
}