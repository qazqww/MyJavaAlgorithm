package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19238_StartTaxi {
    static int[] dy = new int[] { -1, 0, 1, 0 };
    static int[] dx = new int[] { 0, 1, 0, -1 };
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        int customers = Integer.parseInt(st.nextToken());
        int oil = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(in.readLine());
        int taxiY = Integer.parseInt(st.nextToken()) - 1;
        int taxiX = Integer.parseInt(st.nextToken()) - 1;

        int customerNo = 0;
        ArrayList<int[]> goal = new ArrayList<>();
        for (int i = 0; i < customers; i++) {
            st = new StringTokenizer(in.readLine());
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;
            map[startY][startX] += (customerNo++ + 2);
            goal.add(new int[] { endY, endX });
        }

        for (int i = 0; i < customers; i++) {
            int[] customer = getNextCustomer(taxiY, taxiX);
            if (customer[0] == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }

            int no = map[customer[0]][customer[1]] - 2;
            int[] curGoal = goal.get(no);
            taxiY = customer[0];
            taxiX = customer[1];
            oil -= customer[2];
            if (oil < 0) {
                System.out.println(-1);
                return;
            }
            map[taxiY][taxiX] = 0;

            int dist = getGoal(taxiY, taxiX, curGoal[0], curGoal[1]);
            if (dist < 0) {
                System.out.println(-1);
                return;
            }

            oil -= dist;
            if (oil < 0) {
                System.out.println(-1);
                return;
            }

            taxiY = curGoal[0];
            taxiX = curGoal[1];
            oil += dist * 2;
        }

        System.out.println(oil);
    }

    static int[] getNextCustomer(int taxiY, int taxiX) {
        int customerY = Integer.MAX_VALUE;
        int customerX = Integer.MAX_VALUE;
        int dist = -1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { taxiY, taxiX });
        boolean[][] visited = new boolean[N][N];
        visited[taxiY][taxiX] = true;
        while (!queue.isEmpty()) {
            if (customerY != Integer.MAX_VALUE) {
                break;
            }
            int size = queue.size();
            dist++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (map[cur[0]][cur[1]] >= 2) { // 손님을 발견하면
                    if (cur[0] < customerY || (cur[0] == customerY && cur[1] < customerX)) {
                        customerY = cur[0];
                        customerX = cur[1];
                    }
                }

                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];
                    if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] == 1 || visited[ny][nx]) {
                        continue;
                    }
                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }
            }
        }

        return new int[] { customerY, customerX, dist };
    }

    static int getGoal(int taxiY, int taxiX, int goalY, int goalX) {
        int dist = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { taxiY, taxiX });
        boolean[][] visited = new boolean[N][N];
        visited[taxiY][taxiX] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            dist++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == goalY && cur[1] == goalX) {
                    return dist;
                }

                for (int d = 0; d < 4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];
                    if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] == 1 || visited[ny][nx]) {
                        continue;
                    }
                    queue.offer(new int[] { ny, nx });
                    visited[ny][nx] = true;
                }
            }
        }

        return -1;
    }
}