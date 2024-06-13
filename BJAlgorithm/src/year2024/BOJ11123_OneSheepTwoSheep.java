package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11123_OneSheepTwoSheep {
    static int[] dy = new int[] { 0, 1, 0, -1 };
    static int[] dx = new int[] { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; t++) {
            String[] temp = in.readLine().split(" ");
            int H = Integer.parseInt(temp[0]);
            int W = Integer.parseInt(temp[1]);
            char[][] map = new char[H][W];
            for (int i = 0; i < H; i++) {
                map[i] = in.readLine().toCharArray();
            }

            int answer = 0;
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (visited[i][j] || map[i][j] == '.') {
                        continue;
                    }
                    answer++;
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int ny = cur[0] + dy[d];
                            int nx = cur[1] + dx[d];
                            if (ny >= H || ny < 0 || nx >= W || nx < 0
                                    || visited[ny][nx] || map[ny][nx] == '.') {
                                continue;
                            }

                            queue.offer(new int[] { ny, nx });
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
            System.out.println(answer);
        }
    }
}