package year2024;

/*
 실패 사유)
 메모리 초과로 실패, N >= 8일 때 시간 초과도 발생할 것으로 예상

 해결 방안)
 체스판의 특성(흰 칸, 검은 칸)을 활용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1799_Bishop {
    static int[] dy = { -1, -1, 1, 1 };
    static int[] dx = { -1, 1, 1, -1 };
    static int N, answer;
    static boolean[][] map;
    static boolean[][] newMap;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        answer = 0;
        int empty = 0;
        map = new boolean[N][N]; // true : 채워짐 (놓을 수 없음)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
                if (!map[i][j]) {
                    empty++;
                }
            }
        }
        for (int i = 0; i < N * N; i++) {
            if (!map[i / N][i % N]) {
                setBishop(i, map, 0, empty);
                break;
            }
        }
        System.out.println(answer);
    }

    static void setBishop(int unit, boolean[][] map, int cnt, int empty) {
        if (unit >= N * N || cnt + empty < answer) {
            return;
        }
        // 놓지 않는 경우
        setBishop(unit + 1, map, cnt, empty);

        // 놓는 경우
        if (!map[unit / N][unit % N]) {
            newMap = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(map[i], 0, newMap[i], 0, N);
            }
            int filled = 1;
            newMap[unit / N][unit % N] = true;
            answer = Math.max(answer, ++cnt);
            filled += checkMap(unit / N, unit % N);
            setBishop(unit + 1, newMap, cnt, empty - filled);
        }
    }

    static int checkMap(int y, int x) {
        int filled = 0;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            while (ny < N && ny >= 0 && nx < N && nx >= 0) {
                if (!newMap[ny][nx]) {
                    newMap[ny][nx] = true;
                    filled++;
                }
                ny += dy[d];
                nx += dx[d];
            }
        }
        return filled;
    }
}