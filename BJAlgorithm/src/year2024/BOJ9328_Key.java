package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9328_Key {
    static final int[] DY = new int[] { -1, 0, 1, 0 };
    static final int[] DX = new int[] { 0, 1, 0, -1 };

    static char[][] map;
    static boolean[][] visited;
    static int H, W, answer;
    static Set<Character> key;
    static Map<Character, ArrayList<int[]>> locked;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; t++) {
            String[] temp = in.readLine().split(" ");
            H = Integer.parseInt(temp[0]);
            W = Integer.parseInt(temp[1]);
            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                map[i] = in.readLine().toCharArray();
            }

            String keyTemp = in.readLine();
            key = new HashSet<>();
            for (char ch : keyTemp.toCharArray()) {
                key.add(ch);
            }
            locked = new HashMap<>();
            answer = 0;

            visited = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                if (map[i][0] != '*' && !visited[i][0]) {
                    search(i, 0);
                }
                if (map[i][W - 1] != '*' && !visited[i][W - 1]) {
                    search(i, W - 1);
                }
            }
            for (int i = 1; i < W - 1; i++) {
                if (map[0][i] != '*' && !visited[0][i]) {
                    search(0, i);
                }
                if (map[H - 1][i] != '*' && !visited[H - 1][i]) {
                    search(H - 1, i);
                }
            }
            System.out.println(answer);
        }
    }

    static void search(int h, int w) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { h, w });
        visited[h][w] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];

            switch (map[y][x]) {
                case '*':
                    continue;
                case '.':
                    break;
                case '$':
                    answer++;
                    break;
                default:
                    // 1. 열쇠인 경우
                    if (map[y][x] >= 'a' && map[y][x] <= 'z') {
                        key.add(map[y][x]);
                        if (locked.containsKey(map[y][x])) {
                            for (int[] pos : locked.get(map[y][x])) {
                                queue.offer(pos);
                            }
                        }
                    }
                    // 2. 문인 경우
                    else if (map[y][x] >= 'A' && map[y][x] <= 'Z') {
                        char keyChar = (char)(map[y][x] + 32);
                        if (!key.contains(keyChar)) {
                            if (!locked.containsKey(keyChar)) {
                                locked.put(keyChar, new ArrayList<>());
                            }
                            locked.get(keyChar).add(new int[] { y, x });
                            continue;
                        }
                    }
                    break;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + DY[d];
                int nx = cur[1] + DX[d];

                if (ny >= H || ny < 0 || nx >= W || nx < 0 || visited[ny][nx]) {
                    continue;
                }

                queue.offer(new int[] { ny, nx });
                visited[ny][nx] = true;
            }
        }
    }
}
/*
1. 테두리로부터 완전 탐색
2. 탐색하다 마주치는 열 수 없는 문을 기록
3. 새 열쇠를 주우면 새로 열 수 있는 문을 추가 탐색
 */