package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1938_LogMoving {
    static int[] cur = new int[] { -1, -1, -1 };
    static int[] goal = new int[] { -1, -1, -1 };
    static int N;
    static char[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new char[N][N];
        visited = new boolean[N][N][2];

        for (int i = 0; i < N; i++) {
            String temp = in.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j);

                // 시작 위치과 끝 위치를 저장
                if (map[i][j] == 'B') {
                    if (cur[0] == -1 && i - 1 >= 0 && map[i - 1][j] == 'B') {
                        cur = new int[] { i, j, 1 };
                        visited[i][j][1] = true;
                    }
                    else if (cur[0] == -1 && j - 1 >= 0 && map[i][j - 1] == 'B') {
                        cur = new int[] { i, j, 0 };
                        visited[i][j][0] = true;
                    }
                }
                else if (map[i][j] == 'E') {
                    if (goal[0] == -1 && i - 1 >= 0 && map[i - 1][j] == 'E') {
                        goal = new int[] { i, j, 1 };
                    }
                    else if (goal[0] == -1 && j - 1 >= 0 && map[i][j - 1] == 'E') {
                        goal = new int[] { i, j, 0 };
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(cur);
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();

                // 도착 위치에 도달할 시 탐색 종료
                if (point[0] == goal[0] && point[1] == goal[1] && point[2] == goal[2]) {
                    System.out.println(cnt);
                    return;
                }

                // 사방 탐색과 회전 가능 여부를 판별하여 BFS 식으로 진행
                for (int j = 0; j < 5; j++) {
                    switch (j) {
                        case 0:
                            if (checkUp(point[0], point[1], point[2])) {
                                visited[point[0] - 1][point[1]][point[2]] = true;
                                queue.offer(new int[] { point[0] - 1, point[1], point[2] });
                            }
                        case 1:
                            if (checkRight(point[0], point[1], point[2])) {
                                visited[point[0]][point[1] + 1][point[2]] = true;
                                queue.offer(new int[] { point[0], point[1] + 1, point[2] });
                            }
                        case 2:
                            if (checkDown(point[0], point[1], point[2])) {
                                visited[point[0] + 1][point[1]][point[2]] = true;
                                queue.offer(new int[] { point[0] + 1, point[1], point[2] });
                            }
                        case 3:
                            if (checkLeft(point[0], point[1], point[2])) {
                                visited[point[0]][point[1] - 1][point[2]] = true;
                                queue.offer(new int[] { point[0], point[1] - 1, point[2] });
                            }
                        case 4:
                            if (checkAll(point[0], point[1], point[2])) {
                                visited[point[0]][point[1]][1 - point[2]] = true;
                                queue.offer(new int[] { point[0], point[1], 1 - point[2] });
                            }
                    }
                }
            }
            cnt++;
        }
        System.out.println(0);
    }

    static boolean checkUp(int y, int x, int state) {
        if (y - 1 < 0 || visited[y - 1][x][state]) {
            return false;
        }
        if (state == 0) {
            for (int i = -1; i <= 1; i++) {
                if (map[y - 1][x + i] == '1') {
                    return false;
                }
            }
            return true;
        }
        if (state == 1 && y - 2 >= 0 && map[y - 2][x] != '1') {
            return true;
        }
        return false;
    }

    static boolean checkDown(int y, int x, int state) {
        if (y + 1 >= N || visited[y + 1][x][state]) {
            return false;
        }
        if (state == 0) {
            for (int i = -1; i <= 1; i++) {
                if (map[y + 1][x + i] == '1') {
                    return false;
                }
            }
            return true;
        }
        if (state == 1 && y + 2 < N && map[y + 2][x] != '1') {
            return true;
        }
        return false;
    }

    static boolean checkLeft(int y, int x, int state) {
        if (x - 1 < 0 || visited[y][x - 1][state]) {
            return false;
        }
        if (state == 1) {
            for (int i = -1; i <= 1; i++) {
                if (map[y + i][x - 1] == '1') {
                    return false;
                }
            }
            return true;
        }
        if (state == 0 && x - 2 >= 0 && map[y][x - 2] != '1') {
            return true;
        }
        return false;
    }

    static boolean checkRight(int y, int x, int state) {
        if (x + 1 >= N || visited[y][x + 1][state]) {
            return false;
        }
        if (state == 1) {
            for (int i = -1; i <= 1; i++) {
                if (map[y + i][x + 1] == '1') {
                    return false;
                }
            }
            return true;
        }
        if (state == 0 && x + 2 < N && map[y][x + 2] != '1') {
            return true;
        }
        return false;
    }

    static boolean checkAll(int y, int x, int state) {
        if (y - 1 < 0 || y + 1 >= N || x - 1 < 0 || x + 1 >= N || visited[y][x][1 - state]) {
            return false;
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (map[y + i][x + j] == '1') {
                    return false;
                }
            }
        }
        return true;
    }
}