package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17822_DiskRotate {
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] disk = new int[N][M];
        boolean[][] visited;
        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                disk[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 디스크 회전
            for (int i = x - 1; i < N; i += x) {
                disk[i] = rotateDisk(disk[i], d, k);
            }

            // 인접한 수 탐색
            visited = new boolean[N][M];
            Queue<int[]> queue;
            int cnt = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (visited[r][c] || disk[r][c] == 0) {
                        continue;
                    }

                    int num = disk[r][c];
                    boolean isSame = false;
                    queue = new LinkedList<>();
                    queue.offer(new int[] { r, c });
                    visited[r][c] = true;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int dir = 0; dir < 4; dir++) {
                            int nr = cur[0] + dy[dir];
                            int nc = cur[1] + dx[dir];

                            if (nr >= N || nr < 0){
                                continue;
                            }

                            if (nc == M) {
                                nc = 0;
                            }
                            else if (nc == -1) {
                                nc = M - 1;
                            }

                            if (disk[nr][nc] == num) {
                                isSame = true;
                                queue.offer(new int[] { nr, nc });
                                disk[nr][nc] = 0;
                                visited[nr][nc] = true;
                            }
                        }
                    }
                    if (isSame) {
                        disk[r][c] = 0;
                        cnt++;
                    }
                }
            }

            if (cnt == 0) {
                int sum = 0;
                int num = 0;
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (disk[r][c] != 0) {
                            sum += disk[r][c];
                            num++;
                        }
                    }
                }
                if (num != 0) {
                    double avg = (double) sum / (double) num;
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < M; c++) {
                            if (disk[r][c] != 0) {
                                if (avg > disk[r][c]) {
                                    disk[r][c]++;
                                } else if (avg < disk[r][c]) {
                                    disk[r][c]--;
                                }
                            }
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                answer += disk[r][c];
            }
        }
        System.out.println(answer);
    }

    static int[] rotateDisk(int[] disk, int dir, int rotate) { // dir 0 + , dir 1 -
        int[] temp = new int[M];
        rotate %= M;
        if (dir == 0) {
            for (int i = 0, idx = rotate; i < M; i++, idx++) {
                if (idx >= M) {
                    idx = 0;
                }
                temp[idx] = disk[i];
            }
        }
        else {
            for (int i = rotate, idx = 0; idx < M; i++, idx++) {
                if (i >= M) {
                    i = 0;
                }
                temp[idx] = disk[i];
            }
        }
        return temp;
    }
}