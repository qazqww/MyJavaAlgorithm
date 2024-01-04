package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189_ComebackHome {
    static int[] dy = new int[] { -1, 0, 1, 0 };
    static int[] dx = new int[] { 0, 1, 0, -1 };
    static int R, C, K, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = 0;
        char[][] map = new char[R][];

        for (int i = 0; i < R; i++) {
            String str = in.readLine();
            map[i] = str.toCharArray();
        }

        map[R - 1][0] = 'X';
        find(map, R - 1, 0, 1);
        System.out.println(answer);
    }

    static void find(char[][] map, int r, int c, int step) {
        if (r == 0 && c == C - 1) {
            if (step == K) {
                answer++;
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dy[d];
            int nc = c + dx[d];

            if (nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc] != '.') {
                continue;
            }

            map[nr][nc] = 'X';
            find(map, nr, nc, step + 1);
            map[nr][nc] = '.';
        }
    }
}