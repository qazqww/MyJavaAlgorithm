package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1944_DuplicatedRobot {
    public static void main(String[] args) throws IOException {
        final int[] DY = new int[] { -1, 0, 1, 0 };
        final int[] DX = new int[] { 0, 1, 0, -1 };

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);
        int[][] map = new int[N][N];
        int[][] keyList = new int[M + 1][]; // 로봇과 키에 대한 위치 정보를 묶어서 관리
        int keyIdx = 1;
        ArrayList<int[]>[] adjList = new ArrayList[M + 1]; // 키 간의 거리 정보를 저장
        for (int i = 0; i < M + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String temp2 = in.readLine();
            for (int j = 0; j < N; j++) {
                // 미로 정보를 풀이 방법에 맞춰 리매핑
                switch (temp2.charAt(j)) {
                    case '0': // 빈 칸은 -1
                        map[i][j] = -1;
                        break;
                    case '1': // 벽은 -2
                        map[i][j] = -2;
                        break;
                    case 'S': // 0과 양수값은 로봇과 키가 각자의 index값으로 지니도록 설정
                        map[i][j] = 0;
                        keyList[0] = new int[] { i, j };
                        break;
                    case 'K':
                        map[i][j] = keyIdx;
                        keyList[keyIdx++] = new int[] { i, j };
                        break;
                }
            }
        }

        // 1. 각 키의 위치에서 BFS를 진행하여, 키 간의 위치 관계를 정리한다
        for (int i = 0; i < M + 1; i++) {
            int curY = keyList[i][0];
            int curX = keyList[i][1];
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            queue.offer(new int[] { curY, curX });
            visited[curY][curX] = true;

            int dist = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                dist++;
                for (int j = 0; j < size; j++) {
                    int[] cur = queue.poll();
                    for (int d = 0; d < 4; d++) {
                        int ny = cur[0] + DY[d];
                        int nx = cur[1] + DX[d];

                        if (ny >= N || ny < 0 || nx >= N || nx < 0
                                || map[ny][nx] == -2 || visited[ny][nx]) {
                            continue;
                        }

                        queue.offer(new int[] { ny, nx });
                        visited[ny][nx] = true;
                        if (map[ny][nx] >= 0) {
                            adjList[i].add(new int[] { map[ny][nx], dist });
                        }
                    }
                }
            }
        }

        // 2. 정리한 정보를 토대로 최소 신장 트리를 구한다.
        boolean[] visited = new boolean[M + 1];
        visited[0] = true;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int[] arr : adjList[0]) {
            pq.offer(arr);
        }

        int answer = 0;
        int visitedCnt = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (visited[cur[0]]) {
                continue;
            }

            answer += cur[1];
            visited[cur[0]] = true;
            visitedCnt++;

            for (int[] next : adjList[cur[0]]) {
                if (!visited[next[0]]) {
                    pq.offer(next);
                }
            }
        }

        // 3. 모든 키를 방문했다면 결과값을, 아니면 -1을 출력한다.
        System.out.println(visitedCnt == M ? answer : -1);
    }
}