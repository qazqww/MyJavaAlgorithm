// https://www.acmicpc.net/problem/2166

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2166_PolygonArea {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Point(x, y));
        }

        double total = 0.0;
        Point p1 = list.get(N - 1);
        Point p2 = list.get(0);
        total += (p1.x * p2.y - p2.x * p1.y);

        for (int i = 0; i < N - 1; i++) {
            p1 = list.get(i);
            p2 = list.get(i + 1);
            total += (p1.x * p2.y - p2.x * p1.y);
        }

        double answer = (double)Math.abs(total) / 2;

        System.out.printf("%.1f", answer);
    }

    static class Point {
        double x;
        double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}