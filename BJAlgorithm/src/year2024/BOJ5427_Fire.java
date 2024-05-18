package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ5427_Fire {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        testCase: for (int t = 0; t < T; t++) {
            String[] temp = in.readLine().split(" ");
            int w = Integer.parseInt(temp[0]);
            int h = Integer.parseInt(temp[1]);
            char[][] map = new char[h][w];
            int[] dy = new int[] { -1, 0, 1, 0 };
            int[] dx = new int[] { 0, 1, 0, -1 };

            Queue<int[]> fire = new LinkedList<>();
            Queue<int[]> me = new LinkedList<>();
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                map[i] = in.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '*') {
                        fire.offer(new int[] { i, j });
                    }
                    else if (map[i][j] == '@') {
                        me.offer(new int[] { i, j });
                        visited[i][j] = true;
                    }
                }
            }

            int time = 0;
            while (!me.isEmpty()) {
                time++;
                int fireLen = fire.size();
                for (int i = 0; i < fireLen; i++) {
                    int[] curFire = fire.poll();
                    for (int d = 0; d < 4; d++) {
                        int ny = curFire[0] + dy[d];
                        int nx = curFire[1] + dx[d];

                        if (ny >= h || ny < 0 || nx >= w || nx < 0
                                || map[ny][nx] == '#' || map[ny][nx] == '*') {
                            continue;
                        }

                        map[ny][nx] = '*';
                        fire.offer(new int[] { ny, nx });
                    }
                }

                int meLen = me.size();
                for (int i = 0; i < meLen; i++) {
                    int[] cur = me.poll();
                    for (int d = 0; d < 4; d++) {
                        int ny = cur[0] + dy[d];
                        int nx = cur[1] + dx[d];

                        if (ny >= h || ny < 0 || nx >= w || nx < 0) {
                            System.out.println(time);
                            continue testCase;
                        }

                        if (visited[ny][nx] || map[ny][nx] == '#' || map[ny][nx] == '*') {
                            continue;
                        }

                        visited[ny][nx] = true;
                        me.offer(new int[] { ny, nx });
                    }
                }
            }
            System.out.println("IMPOSSIBLE");
        }
    }
}