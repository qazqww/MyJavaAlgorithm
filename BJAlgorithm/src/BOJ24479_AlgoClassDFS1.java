import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ24479_AlgoClassDFS1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()) - 1;
        ArrayList<Integer>[] list = new ArrayList[N];
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            points[i] = new Point();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            list[start].add(end);
            list[end].add(start);
        }

        for (int i = 0; i < N; i++) {
            Collections.sort(list[i]);
        }

        dfs(points, list, R);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(points[i].order + "\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void dfs(Point[] points, ArrayList<Integer>[] list, int now) {
        points[now].order = Point.cnt++;
        points[now].visited = true;
        for (int next : list[now]) {
            if (!points[next].visited) {
                dfs(points, list, next);
            }
        }
    }

    static class Point {
        int order;
        boolean visited;
        static int cnt = 1;

        public Point() {
            this.order = 0;
            this.visited = false;
        }
    }
}