package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6146_ToMeetSinah {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int X = Integer.parseInt(st.nextToken()) + 500;
        int Y = Integer.parseInt(st.nextToken()) + 500;
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[1001][1001];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            map[Integer.parseInt(st.nextToken()) + 500][Integer.parseInt(st.nextToken()) + 500] = -1;
        }

        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 500, 500 });
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx < 0 || nx > 1000 || ny < 0 || ny > 1000 || map[nx][ny] < 0) {
                    continue;
                }

                if (map[nx][ny] == 0 || map[nx][ny] > map[cur[0]][cur[1]] + 1) {
                    map[nx][ny] = map[cur[0]][cur[1]] + 1;
                    queue.offer(new int[] { nx, ny });
                }
            }
        }

        System.out.println(map[X][Y]);
    }
}
/*
-500 ~ 0 ~ 500
1001
 */