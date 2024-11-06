package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2157_Travel {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] route = new int[N][]; // 주어진 경로를 저장하는 배열
        for (int i = 0; i < N; i++) {
            route[i] = new int[N - 1 - i];
        }
        int[][] dp = new int[N][M]; // N번 도시에 M번 만에 도달할 때의 점수를 저장하는 배열

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            // 필요없는 경로를 배제하는 코드
            if (a > b) {
                continue;
            }
            route[a][N - 1 - b] = Math.max(route[a][N - 1 - b], c);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int time = 0;

        while (!queue.isEmpty() && time < M - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int j = 0; j < route[cur].length; j++) {

                    // 경로가 없는 경우는 패스
                    if (route[cur][j] == 0) {
                        continue;
                    }

                    // 값이 갱신되는 경우만 탐색하도록 설정
                    if (dp[N - 1 - j][time + 1] < dp[cur][time] + route[cur][j]) {
                        dp[N - 1 - j][time + 1] = dp[cur][time] + route[cur][j];
                        queue.offer(N - 1 - j);
                    }
                }
            }
            time++;
        }

        int answer = 0;
        for (int i = 0; i < dp[0].length; i++) {
            answer = Math.max(answer, dp[N - 1][i]);
        }
        System.out.println(answer);
    }
}
/*
a ~ b 경로에 대해
적은 경로로 더 큰 값을 낼 수 있으면

b로 향하는 경로가 있는 a

5 4 6
1 2 10
2 3 10
3 4 10
4 5 10
1 3 15
3 5 15
 */