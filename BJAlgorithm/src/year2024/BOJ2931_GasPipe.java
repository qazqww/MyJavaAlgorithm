package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2931_GasPipe {
    static final int[] DY = new int[] {-1, 0, 1, 0};
    static final int[] DX = new int[] {0, 1, 0, -1};

    static int R, C;
    static int[][] map;
    static Queue<int[]> queue = new LinkedList<>();
    static Map<Character, Integer> blockDict = Map.of( // 파이프 정보를 비트값을 쓰는 int로 저장
            'M', 0,
            'Z', 0,
            '.', 0,
            '|', 5,
            '-', 10,
            '+', 15,
            '1', 6,
            '2', 3,
            '3', 9,
            '4', 12
    );

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);
        map = new int[R][C];
        boolean[][] visited = new boolean[R][C];
        int[] moscow = new int[2];

        for (int i = 0; i < R; i++) {
            String line = in.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = blockDict.get(line.charAt(j));
                if (line.charAt(j) == 'M') {
                    moscow[0] = i;
                    moscow[1] = j;
                }
            }
        }

        // 1. 시작점으로 삼을 수 있도록 모스코바에 직접적으로 연결된 파이프를 탐색
        visited[moscow[0]][moscow[1]] = true;
        for (int d = 0; d < 4; d++) {
            int ny = moscow[0] + DY[d];
            int nx = moscow[1] + DX[d];

            if (ny >= R || ny < 0 || nx >= C || nx < 0 || map[ny][nx] == 0) {
                continue;
            }
            queue.offer(new int[] { ny, nx });
            visited[ny][nx] = true;
        }

        // 2. 시작점에서 연결된 파이프를 탐색해나감
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {

                // 뚫린 방향만 탐색 (d 순서 : { 위, 오른쪽, 아래, 왼쪽 })
                if ((map[cur[0]][cur[1]] & (1 << d)) >= 1) {
                    int ny = cur[0] + DY[d];
                    int nx = cur[1] + DX[d];

                    if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx]) {
                        continue;
                    }

                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;

                    // 3. 빈칸을 만나면 정답 후보로 판단, 계산을 시작
                    if (map[ny][nx] == 0) {
                        tryAnswer(ny, nx);
                    }
                }
            }
        }
    }

    static void tryAnswer(int y, int x) {
        int answer = 0;
        for (int d = 0; d < 4; d++) {
            int ny = y + DY[d];
            int nx = x + DX[d];

            if (ny >= R || ny < 0 || nx >= C || nx < 0) {
                continue;
            }

            // 주변 파이프가 현재 방향으로 뚫려있는지 확인
            int reverse = (d + 2) % 4;
            if ((map[ny][nx] & (1 << reverse)) >= 1) {
                answer += (1 << d);
            }
        }
        char lastBlock = ' ';
        for (char key : blockDict.keySet()) {
            if (blockDict.get(key) == answer) {
                lastBlock = key;
                break;
            }
        }

        // 4-1. 적합한 위치가 아닌 경우 다시 탐색으로 돌아감
        if (lastBlock == ' ') {
            return;
        }

        // 4-2. 적합한 위치일 경우 답을 출력하고 곧바로 프로그램 종료
        System.out.println((y + 1) + " " + (x + 1) + " " + lastBlock);
        System.exit(0);
    }
}