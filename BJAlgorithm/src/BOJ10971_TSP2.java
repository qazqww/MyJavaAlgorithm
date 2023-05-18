import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10971_TSP2 {
    static int N, min;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        min = 100_000_000;
        map = new int[N][N];
        boolean[] selected = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            selected[i] = true;
            select(0, i, i, 0, selected);
            selected[i] = false;
        }
        System.out.println(min);
    }

    static void select(int index, int start, int last, int val, boolean[] selected) {
        if (index == N - 1) {
            if (map[last][start] != 0) {
                val += map[last][start];
                min = Math.min(min, val);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (selected[i] || map[last][i] == 0) {
                continue;
            }
            selected[i] = true;
            val += map[last][i];
            select(index + 1, start, i, val, selected);
            selected[i] = false;
            val -= map[last][i];
        }
    }
}