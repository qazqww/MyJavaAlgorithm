package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1956_Workout {
    static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] map = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(map[i], INF);
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int value = Integer.parseInt(st.nextToken());
            map[start][end] = value;
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (map[i][j] == INF) {
                    continue;
                }
                for (int k = 0; k < V; k++) {
                    if (map[j][k] == INF) {
                        continue;
                    }
                    map[i][k] = Math.min(map[i][k], map[i][j] + map[j][k]);
                }
            }
        }
        int answer = INF;
        for (int i = 0; i < V; i++) {
            answer = Math.min(answer, map[i][i]);
        }
        System.out.println(answer == INF ? -1 : answer);
    }
}