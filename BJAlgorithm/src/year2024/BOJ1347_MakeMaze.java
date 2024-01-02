package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1347_MakeMaze {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        String str = in.readLine();

        int[] dy = new int[] { -1, 0, 1, 0 };
        int[] dx = new int[] { 0, 1, 0, -1 };
        int[] size = new int[4];
        int y = 0;
        int x = 0;
        int dir = 2;

        for (char ch : str.toCharArray()) {
            switch(ch) {
                case 'R':
                    dir = dir == 3 ? 0 : dir + 1;
                    break;
                case 'L':
                    dir = dir == 0 ? 3 : dir - 1;
                    break;
                case 'F':
                    y += dy[dir];
                    x += dx[dir];
                    size[0] = Math.min(size[0], y);
                    size[1] = Math.max(size[1], x);
                    size[2] = Math.max(size[2], y);
                    size[3] = Math.min(size[3], x);
                    break;
            }
        }

        int h = size[2] - size[0] + 1;
        int w = size[1] - size[3] + 1;
        int ny = Math.abs(size[0]);
        int nx = Math.abs(size[3]);
        char[][] map = new char[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(map[i], '#');
        }
        map[ny][nx] = '.';
        dir = 2;

        for (char ch : str.toCharArray()) {
            switch(ch) {
                case 'R':
                    dir = dir == 3 ? 0 : dir + 1;
                    break;
                case 'L':
                    dir = dir == 0 ? 3 : dir - 1;
                    break;
                case 'F':
                    ny += dy[dir];
                    nx += dx[dir];
                    map[ny][nx] = '.';
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}