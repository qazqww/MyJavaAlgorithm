package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21609_SharkMiddleSchool {
    final static int EMPTY = -10;
    static int N;
    static int[] dy = new int[] { -1, 0, 1, 0 };
    static int[] dx = new int[] { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int score = 0;

        while (true) {
            // 1. 크기가 가장 큰 블록 그룹 찾기
            int blockCnt = 0;
            int rainbowCnt = 0;
            int biggestY = -1;
            int biggestX = -1;
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= 0 || visited[i][j]) {
                        continue;
                    }

                    Queue<int[]> queue = new LinkedList<>();
                    ArrayList<int[]> rainbowVisited = new ArrayList<>();
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                    int blockCntTemp = 1;
                    int rainbowCntTemp = 0;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int ny = cur[0] + dy[d];
                            int nx = cur[1] + dx[d];

                            if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx]
                                || (map[ny][nx] != map[i][j] && map[ny][nx] != 0)) {
                                continue;
                            }

                            blockCntTemp++;
                            queue.offer(new int[] { ny, nx });
                            if (map[ny][nx] == 0) {
                                rainbowVisited.add(new int[] { ny, nx });
                                rainbowCntTemp++;
                            }
                            visited[ny][nx] = true;
                        }
                    }

                    if (blockCnt < blockCntTemp || (blockCnt == blockCntTemp && rainbowCnt <= rainbowCntTemp)) {
                        blockCnt = blockCntTemp;
                        rainbowCnt = rainbowCntTemp;
                        biggestY = i;
                        biggestX = j;
                    }

                    for (int[] cur : rainbowVisited) {
                        visited[cur[0]][cur[1]] = false;
                    }
                }
            }

            // 제거할 블록 그룹이 없으면 종료
            if (blockCnt < 2) {
                System.out.println(score);
                break;
            }
            score += (int) Math.pow(blockCnt, 2);

            // 2. 블록 그룹의 모든 블록 제거
            Queue<int[]> queue = new LinkedList<>();
            visited = new boolean[N][N];
            queue.offer(new int[] { biggestY, biggestX });
            visited[biggestY][biggestX] = true;
            int color = map[biggestY][biggestX];
            map[biggestY][biggestX] = EMPTY;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];

                    if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx]
                            || (map[ny][nx] != color && map[ny][nx] != 0)) {
                        continue;
                    }

                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                    map[ny][nx] = EMPTY;
                }
            }

            // 3. 중력 적용
            gravity(map);
            // 4. 90도 반시계 방향 회전
            map = counterclockwise90(map);
            // 5. 중력 적용
            gravity(map);
        }
    }

    static void gravity(int[][] map) {
        for (int x = 0; x < N; x++) {
            loop: for (int y = N - 1; y >= 0; y--) {
                if (map[y][x] != EMPTY) {
                    continue;
                }

                int ny = y - 1;
                while (ny >= 0) {
                    if (map[ny][x] == -1) {
                        y = ny;
                        continue loop;
                    }
                    if (map[ny][x] >= 0) {
                        map[y][x] = map[ny][x];
                        map[ny][x] = EMPTY;
                        continue loop;
                    }
                    ny--;
                }
            }
        }
    }

    static int[][] counterclockwise90(int[][] map) {
        int[][] newMap = new int[N][N];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                newMap[y][x] = map[x][N - 1 - y];
            }
        }

        return newMap;
    }

    static void print(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + "\t\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}