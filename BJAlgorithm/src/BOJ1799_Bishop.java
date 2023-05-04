import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1799_Bishop {
    static int n, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(in.readLine());
        boolean[][] board = new boolean[n][n];
        answer = 0;
        int remain = n * n;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                int state = Integer.parseInt(st.nextToken());
                board[i][j] = (state == 0);
                if (state == 0) {
                    remain--;
                }
            }
        }

        while (remain > 0) {
            answer++;
            int minCnt = Integer.MAX_VALUE;
            int minY = -1;
            int minX = -1;

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (board[y][x]) {
                        continue;
                    }

                    int cnt = 1;
                    int nowY = y - 1;
                    int nowX = x - 1;
                    while (nowY >= 0 && nowX >= 0) {
                        if (!board[nowY][nowX]) {
                            cnt++;
                        }
                        nowY -= 1;
                        nowX -= 1;
                    }
                    nowY = y + 1;
                    nowX = x - 1;
                    while (nowY < n && nowX >= 0) {
                        if (!board[nowY][nowX]) {
                            cnt++;
                        }
                        nowY += 1;
                        nowX -= 1;
                    }
                    nowY = y - 1;
                    nowX = x + 1;
                    while (nowY >= 0 && nowX < n) {
                        if (!board[nowY][nowX]) {
                            cnt++;
                        }
                        nowY -= 1;
                        nowX += 1;
                    }
                    nowY = y + 1;
                    nowX = x + 1;
                    while (nowY < n && nowX < n) {
                        if (!board[nowY][nowX]) {
                            cnt++;
                        }
                        nowY += 1;
                        nowX += 1;
                    }

                    if (cnt < minCnt) {
                        minCnt = cnt;
                        minY = y;
                        minX = x;
                    }
                }
            }

            board[minY][minX] = true;
            int nowY = minY - 1;
            int nowX = minX - 1;
            while (nowY >= 0 && nowX >= 0) {
                board[nowY][nowX] = true;
                nowY -= 1;
                nowX -= 1;
            }
            nowY = minY + 1;
            nowX = minX - 1;
            while (nowY < n && nowX >= 0) {
                board[nowY][nowX] = true;
                nowY += 1;
                nowX -= 1;
            }
            nowY = minY - 1;
            nowX = minX + 1;
            while (nowY >= 0 && nowX < n) {
                board[nowY][nowX] = true;
                nowY -= 1;
                nowX += 1;
            }
            nowY = minY + 1;
            nowX = minX + 1;
            while (nowY < n && nowX < n) {
                board[nowY][nowX] = true;
                nowY += 1;
                nowX += 1;
            }

            remain -= minCnt;
        }

        System.out.println(answer);
    }
}