import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21736_OldmanNeedFriend {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        int[] me = new int[2];
        for (int i = 0; i < N; i++) {
            String temp = in.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j);
                if (map[i][j] == 'I') {
                    me[0] = i;
                    me[1] = j;
                }
            }
        }

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        visited[me[0]][me[1]] = true;
        queue.offer(new int[] { me[0], me[1] });
        int answer = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];
                if (ny >= N || ny < 0 || nx >= M || nx < 0 || visited[ny][nx] || map[ny][nx] == 'X') {
                    continue;
                }

                if (map[ny][nx] == 'P') {
                    answer++;
                }
                visited[ny][nx] = true;
                queue.offer(new int[] { ny, nx });
            }
        }
        System.out.println(answer == 0 ? "TT" : answer);
    }
}