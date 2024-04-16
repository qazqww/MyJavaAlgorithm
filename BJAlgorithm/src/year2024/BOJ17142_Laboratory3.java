package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17142_Laboratory3 {
    static int N, M, empty, answer;
    static int[][] map;
    static ArrayList<int[]> virus;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        selected = new int[M];
        empty = 0;
        answer = Integer.MAX_VALUE;
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                switch (map[i][j]) {
                    case 0:
                        empty++;
                        break;
                    case 2:
                        virus.add(new int[] { i, j });
                        break;
                }
            }
        }
        combi(0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void combi(int index, int num) {
        if (index == M) {
            bfs();
            return;
        }

        for (int i = num; i < virus.size(); i++) {
            selected[index] = i;
            combi(index + 1, i + 1);
        }
    }

    static void bfs() {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, N);
        }

        int time = 0;
        int filled = 0;
        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        Queue<int[]> queue = new LinkedList<>();
        for (int i : selected) {
            queue.offer(virus.get(i));
        }

        while (!queue.isEmpty()) {
            if (filled == empty) {
                break;
            }

            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];

                    if (ny >= N || ny < 0 || nx >= N || nx < 0 || newMap[ny][nx] % 2 == 1) {
                        continue;
                    }

                    if (newMap[ny][nx] == 0) {
                        filled++;
                    }

                    newMap[ny][nx] = 3;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }

        if (filled == empty) {
            answer = Math.min(answer, time);
        }
    }
}