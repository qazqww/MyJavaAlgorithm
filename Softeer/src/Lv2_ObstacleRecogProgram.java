import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lv2_ObstacleRecogProgram {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        boolean[][] map = new boolean[N][N];
        boolean[][] visited = new boolean[N][N];
        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };

        for (int i = 0; i < N; i++) {
            String temp = in.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j) == '1' ? true : false;
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || !map[i][j]) {
                    continue;
                }

                int blockCnt = 0;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[]{ i, j });
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    blockCnt++;
                    for (int d = 0; d < 4; d++) {
                        int ny = now[0] + dy[d];
                        int nx = now[1] + dx[d];

                        if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx] || !map[ny][nx]) {
                            continue;
                        }

                        queue.offer(new int[] { ny, nx });
                        visited[ny][nx] = true;
                    }
                }
                answer.add(blockCnt);
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (int num : answer) {
            System.out.println(num);
        }
    }
}