import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ16724_PiriMan {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][];

        for (int i = 0; i < N; i++) {
            map[i] = in.readLine().toCharArray();
        }

        int cnt = 0;
        boolean newVisit;
        Set<Integer> visited;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    continue;
                }
                int y = i;
                int x = j;
                newVisit = false;
                visited = new HashSet<>();
                while (map[y][x] != '0') {
                    newVisit = true;
                    int nowPoint = y * M + x;
                    if (visited.contains(nowPoint)) {
                        break;
                    }
                    visited.add(nowPoint);
                    char nowChar = map[y][x];
                    switch (nowChar) {
                        case 'U':
                            y--;
                            break;
                        case 'R':
                            x++;
                            break;
                        case 'D':
                            y++;
                            break;
                        case 'L':
                            x--;
                            break;
                    }

                    if (map[y][x] == '0') {
                        newVisit = false;
                    }
                }

                for (int num : visited) {
                    int n = num / M;
                    int m = num % M;
                    map[n][m] = '0';
                }

                if (newVisit) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}