package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1706_Crossword {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] board = new char[R][];
        for (int i = 0; i < R; i++) {
            board[i] = in.readLine().toCharArray();
        }

        PriorityQueue<String> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c <= C; c++) {
                if (c == C || board[r][c] == '#') {
                    if (sb.length() > 1) {
                        pq.offer(sb.toString());
                    }
                    sb.setLength(0);
                }
                else {
                    sb.append(board[r][c]);
                }
            }
            sb.setLength(0);
        }
        for (int c = 0; c < C; c++) {
            for (int r = 0; r <= R; r++) {
                if (r == R || board[r][c] == '#') {
                    if (sb.length() > 1) {
                        pq.offer(sb.toString());
                    }
                    sb.setLength(0);
                }
                else {
                    sb.append(board[r][c]);
                }
            }
            sb.setLength(0);
        }
        System.out.println(pq.poll());
    }
}