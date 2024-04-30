package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16933_BreakWallAndMove3 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][][] visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String temp = in.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        int[] dy = new int[] { 0, 1, 0, -1 };
        int[] dx = new int[] { 1, 0, -1, 0 };
        int answer = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0, 1, 0, 0 }); // y좌표, x좌표, 온 거리, 부순 벽 수, 낮(0)/밤(1)
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == N - 1 && cur[1] == M - 1) {
                answer = Math.min(answer, cur[2]);
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];
                int cnt = cur[2];
                int breakCnt = cur[3];
                int time = cur[4];

                if (ny >= N || ny < 0 || nx >= M || nx < 0) {
                    continue;
                }

                if (map[ny][nx] == 1) {
                    if (breakCnt + 1 <= K) {
                        if (time == 1) {
                            if (!visited[ny][nx][breakCnt + 1]) {
                                visited[ny][nx][breakCnt + 1] = true;
                                queue.offer(new int[]{ny, nx, cnt + 2, breakCnt + 1, time});
                            }
                        }
                        else {
                            if (!visited[ny][nx][breakCnt + 1]) {
                                visited[ny][nx][breakCnt + 1] = true;
                                queue.offer(new int[]{ny, nx, cnt + 1, breakCnt + 1, 1 - time});
                            }
                        }
                    }
                }
                else {
                    if (!visited[ny][nx][breakCnt]) {
                        visited[ny][nx][breakCnt] = true;
                        queue.offer(new int[]{ny, nx, cnt + 1, breakCnt, 1 - time});
                    }
                }
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}