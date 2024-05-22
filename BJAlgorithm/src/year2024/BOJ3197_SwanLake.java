package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ3197_SwanLake {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int R = Integer.parseInt(temp[0]);
        int C = Integer.parseInt(temp[1]);
        char[][] map = new char[R][C];
        ArrayList<int[]> swan = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    swan.add(new int[] { i, j });
                }
            }
        }

        int[] dy = new int[] { 1, 0, -1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        int[] start = swan.get(0);
        int[] end = swan.get(1);

        Queue<int[]> meetQueue = new LinkedList<>();
        Queue<int[]> tempQueue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        meetQueue.offer(new int[] { start[0], start[1] });
        visited[start[0]][start[1]] = true;
        int time = 0;

        // 얼음 녹을 곳 미리 체크
        Queue<int[]> meltQueue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X' || map[i][j] == 'M') {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];

                    if (ny >= R || ny < 0 || nx >= C | nx < 0) {
                        continue;
                    }

                    if (map[ny][nx] == 'X') {
                        meltQueue.offer(new int[] { ny, nx });
                        map[ny][nx] = 'M';
                    }
                }
            }
        }

        while (true) {
            // 만날 수 있는지 탐색
            while (!meetQueue.isEmpty()) {
                int[] cur = meetQueue.poll();
                if (cur[0] == end[0] && cur[1] == end[1]) {
                    System.out.println(time);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];

                    if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx]) {
                        continue;
                    }

                    if (map[ny][nx] == 'M') {
                        tempQueue.offer(new int[] { ny, nx });
                        continue;
                    }

                    meetQueue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }
            }
            while (!tempQueue.isEmpty()) {
                int[] cur = tempQueue.poll();
                if (!visited[cur[0]][cur[1]]) {
                    meetQueue.offer(cur);
                    visited[cur[0]][cur[1]] = true;
                }
            }
            time++;

            int size = meltQueue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = meltQueue.poll();
                map[cur[0]][cur[1]] = '.';

                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];

                    if (ny >= R || ny < 0 || nx >= C | nx < 0) {
                        continue;
                    }

                    if (map[ny][nx] == 'X') {
                        meltQueue.offer(new int[]{ny, nx});
                        map[ny][nx] = 'M';
                    }
                }
            }
        }
    }
}