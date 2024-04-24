package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14226_Emoticon {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] cnt = new int[1025];
        boolean[][] visited = new boolean[1025][1025];
        for (int i = 2; i < cnt.length; i++) {
            cnt[i] = Integer.MAX_VALUE;
        }
        Queue<int[]> queue = new LinkedList<>(); // 숫자, 횟수, 클립보드
        queue.offer(new int[] { 1, 0, 0 });
        visited[1][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int[] copy = new int[] { cur[0], cur[1] + 1, cur[0] };
            if (!visited[copy[0]][copy[2]]) {
                visited[copy[0]][copy[2]] = true;
                queue.offer(copy);
            }

            int[] paste = new int[] { cur[0] + cur[2], cur[1] + 1, cur[2] };
            if (paste[0] < cnt.length && !visited[paste[0]][paste[2]]) {
                visited[paste[0]][paste[2]] = true;
                cnt[paste[0]] = Math.min(cnt[paste[0]], paste[1]);
                queue.offer(paste);
            }

            int[] minus = new int[] { cur[0] - 1, cur[1] + 1, cur[2] };
            if (minus[0] >= 2 && !visited[minus[0]][minus[2]]) {
                visited[minus[0]][minus[2]] = true;
                cnt[minus[0]] = Math.min(cnt[minus[0]], minus[1]);
                queue.offer(minus);
            }
        }
        System.out.println(cnt[N]);
    }
}