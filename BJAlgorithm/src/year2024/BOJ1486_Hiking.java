package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1486_Hiking {
    public static void main(String[] args) throws IOException {
        final int MAX = 100_000_000;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[][][] map = new int[3][N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[1][i], MAX);
            Arrays.fill(map[2][i], MAX);
            String input = in.readLine();
            for (int j = 0; j < M; j++) {
                char ch = input.charAt(j);
                if (ch >= 'A' && ch <= 'Z') {
                    map[0][i][j] = ch - 'A';
                }
                else {
                    map[0][i][j] = ch - 'a' + 26;
                }
            }
        }
        map[1][0][0] = map[2][0][0] = 0;

        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                if (ny >= N || ny < 0 || nx >= M || nx < 0
                        || Math.abs(map[0][ny][nx] - map[0][cur[0]][cur[1]]) > T) {
                    continue;
                }

                int time = map[0][ny][nx] <= map[0][cur[0]][cur[1]] ?
                        1 : (int) Math.pow(map[0][ny][nx] - map[0][cur[0]][cur[1]], 2);
                if (map[1][ny][nx] > map[1][cur[0]][cur[1]] + time) {
                    map[1][ny][nx] = map[1][cur[0]][cur[1]] + time;
                    queue.offer(new int[] { ny, nx });
                }
            }
        }

        queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                if (ny >= N || ny < 0 || nx >= M || nx < 0
                        || Math.abs(map[0][ny][nx] - map[0][cur[0]][cur[1]]) > T) {
                    continue;
                }

                int time = map[0][ny][nx] >= map[0][cur[0]][cur[1]] ?
                        1 : (int) Math.pow(map[0][cur[0]][cur[1]] - map[0][ny][nx], 2);
                if (map[2][ny][nx] > map[2][cur[0]][cur[1]] + time) {
                    map[2][ny][nx] = map[2][cur[0]][cur[1]] + time;
                    queue.offer(new int[] { ny, nx });
                }
            }
        }

        int answer = map[0][0][0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[0][i][j] <= answer) {
                    continue;
                }

                int cost = map[1][i][j] + map[2][i][j];
                if (cost <= D) {
                    answer = map[0][i][j];
                }
            }
        }
        System.out.println(answer);
    }
}