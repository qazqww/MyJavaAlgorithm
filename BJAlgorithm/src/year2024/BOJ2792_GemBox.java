package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2792_GemBox {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] gems = new int[M];

        for (int i = 0; i < M; i++) {
            gems[i] = Integer.parseInt(in.readLine());
        }

        int start = 1;
        int end = 1_000_000_000;
        int answer = -1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int cnt = 0;
            for (int i = 0; i < M; i++) {
                cnt += gems[i] / mid;
                if (gems[i] % mid > 0) {
                    cnt++;
                }
            }

            if (cnt <= N) {
                answer = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }
}

/*
end값 : min(보석의 합 - (학생 수 - 1), 가장 큰 보석 수)
start값 : 보석 / 학생 ?

조건
보석 순차 탐색하며 mid값을 뺌
나오는 cnt 수가 학생 수보다 적으면 ok
 */