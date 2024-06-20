package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13459_BeadEscape {
    static final int MOVE_LIMIT = 10;
    static int[] dy = new int[] { -1, 0, 1, 0 };
    static int[] dx = new int[] { 0, 1, 0, -1 };
    static char[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int[] bead = new int[4];

        for (int i = 0; i < N; i++) {
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    map[i][j] = '.';
                    bead[0] = i;
                    bead[1] = j;
                }
                else if (map[i][j] == 'B') {
                    map[i][j] = '.';
                    bead[2] = i;
                    bead[3] = j;
                }
            }
        }

        tryMove(0, -1, bead);
        System.out.println(0);
    }

    static void tryMove(int idx, int prev, int[] bead) {
        if (idx >= MOVE_LIMIT) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (i == prev) {
                continue;
            }
            int[] result = move(bead, i);
            if (result[0] == -1) {
                continue;
            }
            tryMove(idx + 1, i, result);
        }
    }

    static int[] move(int[] bead, int dir) {
        int redY = bead[0];
        int redX = bead[1];
        int blueY = bead[2];
        int blueX = bead[3];
        boolean redBack = true;
        boolean redGoal = false;

        if ((dir == 0 && redX == blueX && redY < blueY)
            || (dir == 1 && redY == blueY && redX > blueX)
            || (dir == 2 && redX == blueX && redY > blueY)
            || (dir == 3 && redY == blueY && redX < blueX)) {
            redBack = false;
        }

        // #나 O를 만날 때까지 움직인다.
        // R B 위치가 같으면 기존 앞뒤 순서에 맞게 하나를 back

        while (map[redY][redX] == '.') {
            redY += dy[dir];
            redX += dx[dir];
        }
        if (map[redY][redX] == 'O') {
            redGoal = true;
            redY = -100;
            redX = -100;
        }
        redY -= dy[dir];
        redX -= dx[dir];

        while (map[blueY][blueX] == '.') {
            blueY += dy[dir];
            blueX += dx[dir];
        }
        if (map[blueY][blueX] == 'O') {
            return new int[] { -1 };
        }
        blueY -= dy[dir];
        blueX -= dx[dir];

        if (redY == blueY && redX == blueX) {
            if (redBack) {
                redY -= dy[dir];
                redX -= dx[dir];
            }
            else {
                blueY -= dy[dir];
                blueX -= dx[dir];
            }
        }

        if (redGoal) {
            System.out.println(1);
            System.exit(0);
        }

        return new int[] { redY, redX, blueY, blueX };
    }
}