package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1584_Game {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] map = new int[501][501];
        int[][] visited = new int[501][501];
        for (int i = 0; i < 501; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[y][x] = 1;
                }
            }
        }
        int M = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[y][x] = -1;
                }
            }
        }

        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny >= 501 || ny < 0 || nx >= 501 || nx < 0 || map[ny][nx] < 0) {
                    continue;
                }
                if (map[ny][nx] == 1) {
                    if (visited[ny][nx] > visited[y][x] + 1) {
                        visited[ny][nx] = visited[y][x] + 1;
                        queue.offer(new int[] { ny, nx });
                    }
                }
                else {
                    if (visited[ny][nx] > visited[y][x]) {
                        visited[ny][nx] = visited[y][x];
                        queue.offer(new int[] { ny, nx });
                    }
                }
            }
        }
        System.out.println(visited[500][500] == Integer.MAX_VALUE ? -1 : visited[500][500]);
    }
}