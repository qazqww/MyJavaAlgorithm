package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ3184_Sheep {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int R = Integer.parseInt(temp[0]);
        int C = Integer.parseInt(temp[1]);
        char[][] map = new char[R][];
        boolean[][] visited = new boolean[R][C];
        int sheepSum = 0;
        int wolfSum = 0;
        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
        }

        int[] dy = new int[] { 1, 0, -1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (visited[r][c]) {
                    continue;
                }
                queue.offer(new int[] { r, c });
                visited[r][c] = true;
                boolean isOuter = false;
                int sheepCnt = 0;
                int wolfCnt = 0;
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int y = cur[0];
                    int x = cur[1];

                    switch (map[y][x]) {
                        case '#':
                            continue;
                        case 'v':
                            wolfCnt++;
                            break;
                        case 'o':
                            sheepCnt++;
                    }

                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny >= R || ny < 0 || nx >= C || nx < 0) {
                            isOuter = true;
                            continue;
                        }
                        if (visited[ny][nx]) {
                            continue;
                        }
                        queue.offer(new int[] { ny, nx });
                        visited[ny][nx] = true;
                    }
                }
                if (!isOuter) {
                    if (wolfCnt >= sheepCnt) {
                        wolfSum += wolfCnt;
                    }
                    else {
                        sheepSum += sheepCnt;
                    }
                }
            }
        }
        System.out.println(sheepSum + " " + wolfSum);
    }
}