import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18352_FindCityWithDist {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int city = Integer.parseInt(st.nextToken());
        int way = Integer.parseInt(st.nextToken());
        int dist = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken()) - 1;

        ArrayList<Integer>[] list = new ArrayList[city];
        for (int i = 0; i < city; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < way; i++) {
            st = new StringTokenizer(in.readLine());
            list[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] visited = new boolean[city];
        visited[start] = true;

        int cnt = 0;
        ArrayList<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty() && cnt < dist) {
            int size = queue.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                int now = queue.poll();
                for (int next : list[now]) {
                    if (visited[next]) {
                        continue;
                    }
                    if (cnt == dist) {
                        answer.add(next);
                    }
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        if (answer.isEmpty()) {
            System.out.println(-1);
        }
        else {
            Collections.sort(answer);
            for (int num : answer) {
                System.out.println(num + 1);
            }
        }
    }
}