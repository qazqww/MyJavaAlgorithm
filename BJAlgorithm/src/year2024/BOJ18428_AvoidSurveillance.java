package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ18428_AvoidSurveillance {
    static int N;
    static char[][] map;
    static ArrayList<int[]> teachers = new ArrayList<>();
    static int[] dy = new int[] { 0, 1, 0, -1 };
    static int[] dx = new int[] { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = in.readLine().replaceAll(" ", "").toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'T') {
                    teachers.add(new int[] { i, j });
                }
            }
        }
        setObstacle(0, 0);
        System.out.println("NO");
    }

    static void setObstacle(int num, int index) {
        if (index == 3) {
            for (int[] t : teachers) {
                for (int d = 0; d < 4; d++) {
                    int y = t[0] + dy[d];
                    int x = t[1] + dx[d];
                    while (y < N && y >= 0 && x < N && x >= 0 && map[y][x] != 'O') {
                        if (map[y][x] == 'S') {
                            return;
                        }
                        y += dy[d];
                        x += dx[d];
                    }
                }
            }
            System.out.println("YES");
            System.exit(0);
        }

        for (int i = num; i < N * N; i++) {
            if (map[i / N][i % N] != 'X') {
                continue;
            }
            map[i / N][i % N] = 'O';
            setObstacle(i + 1, index + 1);
            map[i / N][i % N] = 'X';
        }
    }
}