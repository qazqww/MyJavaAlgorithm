package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ23289_HelloHeater {
    static int R, C, K;
    static int[][][] map;
    static boolean[][] visited;
    static int[][] divideMap;
    static ArrayList<int[]> checkList = new ArrayList<>();
    static ArrayList<int[]> heaterList = new ArrayList<>();
    static final int[][] DY = new int[][] {
            new int[] { -1, 0, 1 }, new int[] { -1, 0, 1 }, new int[] { -1, -1, -1 }, new int[] { 1, 1, 1 }
    };
    static final int[][] DX = new int[][] {
            new int[] { 1, 1, 1 }, new int[] { -1, -1, -1 }, new int[] { -1, 0, 1 }, new int[] { -1, 0, 1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C][2];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < C; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 5) {
                    checkList.add(new int[] { i, j });
                }
                else if (num > 0) {
                    heaterList.add(new int[] { i, j, num - 1 });
                }
                // 오른쪽, 왼쪽, 위, 아래
            }
        }
        int W = Integer.parseInt(in.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) { // 위에 벽
                map[y][x][1] |= (1 << 2);
                map[y - 1][x][1] |= (1 << 3);
            }
            else { // 오른쪽 벽
                map[y][x][1] |= (1 << 0);
                map[y][x + 1][1] |= (1 << 1);
            }
        }

        int choco = 0;
        while (true) {
            choco++;
            if (choco > 100) {
                break;
            }
            // 1. 온풍기에서 바람이 나온다
            for (int[] heater : heaterList) {
                visited = new boolean[R][C];
                blowWind(heater[0] + DY[heater[2]][1], heater[1] + DX[heater[2]][1], heater[2], 5);
            }
            // 2. 공기들이 섞인다
            divideMap = new int[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    divideWind(i, j);
                }
            }
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j][0] += divideMap[i][j];
                }
            }
            // 3. 외벽 칸의 온도가 감소한다.
            outerCool();
            boolean isHot = true;
            for (int[] check : checkList) {
                if (map[check[0]][check[1]][0] < K) {
                    isHot = false;
                    break;
                }
            }
            if (isHot) {
                break;
            }
        }
        System.out.println(choco > 100 ? 101 : choco);
    }

    static void print(int type) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j][type] + "\t");
            }
            System.out.println();
        }
    }

    static void blowWind(int y, int x, int dir, int power) {
        map[y][x][0] += power;
        if (power == 1) {
            return;
        }

        for (int d = 0; d < 3; d++) { // 왼 -> 오, 위 -> 아래
            int ny = y + DY[dir][d];
            int nx = x + DX[dir][d];

            if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx]) {
                continue;
            }

            // 벽 판정 넣기
            int wall = 0;
            switch (dir) {
                case 0:
                    wall = (1 << 1);
                    break;
                case 1:
                    wall = (1 << 0);
                    break;
                case 2:
                    wall = (1 << 3);
                    break;
                case 3:
                    wall = (1 << 2);
                    break;
            }

            if ((map[ny][nx][1] & wall) >= 1) {
                continue;
            }

            // 왼쪽 -> 0은 현재의 아래, 2는 위가 막히면 X, 오른쪽 -> 0은 아래, 2는 위
            // 위 -> 0은 왼쪽 2는 오른쪽, 아래 -> 0은 왼쪽, 2는 오른쪽
            if (dir < 2) {
                if (d == 0) { // 위 벽 체크
                    if ((map[y][x][1] & (1 << 2)) >= 1) {
                        continue;
                    }
                }
                else if (d == 2) { // 아래 벽 체크
                    if ((map[y][x][1] & (1 << 3)) >= 1) {
                        continue;
                    }
                }
            }
            else {
                if (d == 0) { // 왼 벽 체크
                    if ((map[y][x][1] & (1 << 1)) >= 1) {
                        continue;
                    }
                }
                else if (d == 2) { // 오른 벽 체크
                    if ((map[y][x][1] & (1 << 0)) >= 1) {
                        continue;
                    }
                }
            }

            visited[ny][nx] = true;
            blowWind(ny, nx, dir, power - 1);
        }
    }

    static void divideWind(int y, int x) {
        // 왼쪽
        if (x > 0 && (map[y][x][1] & (1 << 1)) == 0 && map[y][x][0] > map[y][x-1][0]) {
            int gap = (map[y][x][0] - map[y][x-1][0]) / 4;
            divideMap[y][x] -= gap;
            divideMap[y][x-1] += gap;
        }

        // 위
        if (y > 0 && (map[y][x][1] & (1 << 2)) == 0 && map[y][x][0] > map[y-1][x][0]) {
            int gap = (map[y][x][0] - map[y-1][x][0]) / 4;
            divideMap[y][x] -= gap;
            divideMap[y-1][x] += gap;
        }

        // 오른쪽
        if (x < C - 1 && (map[y][x][1] & (1 << 0)) == 0 && map[y][x][0] > map[y][x+1][0]) {
            int gap = (map[y][x][0] - map[y][x+1][0]) / 4;
            divideMap[y][x] -= gap;
            divideMap[y][x+1] += gap;
        }

        // 아래
        if (y < R - 1 && (map[y][x][1] & (1 << 3)) == 0 && map[y][x][0] > map[y+1][x][0]) {
            int gap = (map[y][x][0] - map[y+1][x][0]) / 4;
            divideMap[y][x] -= gap;
            divideMap[y+1][x] += gap;
        }
    }

    static void outerCool() {
        for (int y = 0; y < R; y++) {
            if (map[y][0][0] > 0) {
                map[y][0][0] -= 1;
            }
            if (map[y][C - 1][0] > 0) {
                map[y][C - 1][0] -= 1;
            }
        }
        for (int x = 1; x < C - 1; x++) {
            if (map[0][x][0] > 0) {
                map[0][x][0] -= 1;
            }
            if (map[R - 1][x][0] > 0) {
                map[R - 1][x][0] -= 1;
            }
        }
    }
}