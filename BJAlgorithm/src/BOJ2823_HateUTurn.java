import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2823_HateUTurn {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        int[] dy = new int[] { 0, 1, 0, -1 };
        int[] dx = new int[] { 1, 0, -1, 0 };
        ArrayList<int[]> roadList = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    roadList.add(new int[] { i, j });
                }
            }
        }

        for (int[] road : roadList) {
            int r = road[0];
            int c = road[1];
            int blocked = 0;

            for (int d = 0; d < 4; d++) {
                int nr = r + dy[d];
                int nc = c + dx[d];
                if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 'X') {
                    blocked++;
                }
            }
            if (blocked == 3) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}