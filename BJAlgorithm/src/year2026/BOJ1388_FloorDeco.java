package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1388_FloorDeco {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = in.readLine().toCharArray();
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '-') {
                    map[i][j] = '.';
                    while (j + 1 < M && map[i][j + 1] == '-') {
                        j++;
                        map[i][j] = '.';
                    }
                    cnt++;
                }
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[j][i] == '|') {
                    map[j][i] = '.';
                    while (j + 1 < N && map[j + 1][i] == '|') {
                        j++;
                        map[j][i] = '.';
                    }
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
