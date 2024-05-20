package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1303_WarBattle {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);
        char[][] map = new char[y][x];
        for (int i = 0; i < y; i++) {
            map[i] = in.readLine().toCharArray();
        }

        int[] dy = new int[] { 0, 1, 0, -1 };
        int[] dx = new int[] { 1, 0, -1, 0 };
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[y][x];
        int WPower = 0;
        int BPower = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (visited[i][j]) {
                    continue;
                }

                queue.offer(new int[] { i, j });
                visited[i][j] = true;
                int soldier = 1;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    for (int d = 0; d < 4; d++) {
                        int ny = cur[0] + dy[d];
                        int nx = cur[1] + dx[d];

                        if (ny >= y || nx >= x || ny < 0 || nx < 0
                                || visited[ny][nx] || map[cur[0]][cur[1]] != map[ny][nx]) {
                            continue;
                        }

                        queue.offer(new int[] { ny, nx });
                        visited[ny][nx] = true;
                        soldier++;
                    }
                }

                if (map[i][j] == 'W') {
                    WPower += Math.pow(soldier, 2);
                }
                else {
                    BPower += Math.pow(soldier, 2);
                }
            }
        }
        System.out.println(WPower + " " + BPower);
    }
}