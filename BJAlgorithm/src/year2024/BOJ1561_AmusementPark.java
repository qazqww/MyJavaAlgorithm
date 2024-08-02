package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1561_AmusementPark {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] time = new int[M];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < M; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        N -= M;
        long start = 0;
        long end = 100_000_000_000L;
        long resultTime = 0;
        long resultCnt = 0;
        while (start <= end) {
            long mid = (start + end) / 2;

            long cnt = 0;
            for (int i = 0; i < M; i++) {
                cnt += mid / time[i];
            }
            if (cnt >= N) {
                resultTime = mid;
                resultCnt = cnt;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }

        N += M;
        resultCnt += M + 1;
        int answer = -1;
        for (int i = M - 1; i >= 0; i--) {
            if (resultTime % time[i] == 0) {
                resultCnt--;
                answer = i;
            }
            if (resultCnt == N) {
                break;
            }
        }
        System.out.println(answer + 1);
    }
}
/*
1 3 5 7 9
1 4 7 10 13
1 10 19 28 37

10000 / 1 = 10000
10000 / 2 = 5000
10000 / 3 = 3333
10000 / 4 = 2500
10000 / 5 = 2000

0   1   2   3   4   5   6   7   8
o   o   o   o   o   o   o   o   o
o       o       o       o       o
o           o           o
o               o               o
o                   o
5   6   8   10  13  15  18  19  22

result % time[i] == 0 인 놀이기구 중
result += M 하고
N이랑 같을때까지 -- 해가면서 i를 찾음

 */