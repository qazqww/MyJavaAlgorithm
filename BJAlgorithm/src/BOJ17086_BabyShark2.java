// https://www.acmicpc.net/problem/17086

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17086_BabyShark2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        List<Point> sharks = new ArrayList<>();
        int result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    sharks.add(new Point(i, j));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    continue;
                }

                int near = Integer.MAX_VALUE;
                for (Point p : sharks) {
                    near = Math.min(near, dist(new Point(i, j), p));
                }
                result = Math.max(result, near);
            }
        }

        System.out.println(result);
    }

    static int dist(Point a, Point b) {
        int result = 0;
        int xGap = Math.abs(a.x - b.x);
        int yGap = Math.abs(a.y - b.y);
        result += Math.min(xGap, yGap);
        result += Math.abs(xGap - yGap);
        return result;
    }

    static class Point {
        int y;
        int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}