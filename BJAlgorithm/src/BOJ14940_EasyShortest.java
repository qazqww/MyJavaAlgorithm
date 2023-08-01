import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940_EasyShortest {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][M];

        int startY = -1;
        int startX = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    startY = i;
                    startX = j;
                }
            }
        }

        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        int[][] result = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        visited[startY][startX] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { startY, startX });

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] now = queue.poll();
                result[now[0]][now[1]] = dist;
                for (int d = 0; d < 4; d++) {
                    int ny = now[0] + dy[d];
                    int nx = now[1] + dx[d];

                    if (ny >= N || ny < 0 || nx >= M || nx < 0 || visited[ny][nx] || board[ny][nx] == 0) {
                        continue;
                    }

                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }
            }
            dist++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append((result[i][j] == 0 && board[i][j] == 1) ? -1 : result[i][j]).append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}