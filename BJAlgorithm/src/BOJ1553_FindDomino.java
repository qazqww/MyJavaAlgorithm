import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ1553_FindDomino {
    static final int ROW = 8;
    static final int COL = 7;
    static int[][] map = new int[ROW][COL];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] isFound = new boolean[ROW][COL];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < ROW; i++) {
            String str = in.readLine();
            for (int j = 0; j < COL; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        find(isFound, 0, 0, set);
        System.out.println(answer);
    }

    static void find(boolean[][] isFound, int r, int c, Set<Integer> set) {
        if (r == ROW - 1 && c == COL - 1) {
            answer++;
            return;
        }
        if (c >= COL) {
            find(isFound, r + 1, 0, set);
            return;
        }
        if (isFound[r][c]) {
            find(isFound, r, c + 1, set);
            return;
        }

        // 세로줄 시도
        if (r + 1 < ROW && !isFound[r + 1][c]) {
            if (!isUsed(set, map[r][c], map[r + 1][c])) {
                set.add(map[r][c] * 10 + map[r + 1][c]);
                isFound[r][c] = true;
                isFound[r + 1][c] = true;
                find(isFound, r, c + 1, set);
                isFound[r + 1][c] = false;
                isFound[r][c] = false;
                set.remove(map[r][c] * 10 + map[r + 1][c]);
            }
        }

        // 가로줄 시도
        if (c + 1 < COL && !isFound[r][c + 1]) {
            if (!isUsed(set, map[r][c], map[r][c + 1])) {
                set.add(map[r][c] * 10 + map[r][c + 1]);
                isFound[r][c] = true;
                isFound[r][c + 1] = true;
                find(isFound, r, c + 1, set);
                isFound[r][c + 1] = false;
                isFound[r][c] = false;
                set.remove(map[r][c] * 10 + map[r][c + 1]);
            }
        }
    }

    static boolean isUsed(Set<Integer> set, int num1, int num2) {
        if (set.contains(num1 * 10 + num2) || set.contains(num2 * 10 + num1)) {
            return true;
        }
        return false;
    }
}