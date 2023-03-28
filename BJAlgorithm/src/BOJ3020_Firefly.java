// https://www.acmicpc.net/problem/3020

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3020_Firefly {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] fromDown = new int[H];
        int[] fromUp = new int[H];

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(in.readLine());

            if (i % 2 == 0) {
                fromDown[H - height]++;
            }
            else {
                fromUp[H - height]++;
            }
        }

        for (int i = 1; i < H; i++) {
            fromDown[i] += fromDown[i - 1];
            fromUp[i] += fromUp[i - 1];
        }

        for (int i = 0; i < H; i++) {
            fromDown[i] += fromUp[H - 1 - i];
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            if (min > fromDown[i]) {
                min = fromDown[i];
                cnt = 1;
            }
            else if (min == fromDown[i]) {
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
    }
}
/*
1. 석순을 긴거부터 나열
2. 각 개수를 저장
3. 누적합 배열 생성

-1 3 -4 2 -2 4 -3 4 -3 3 -3 2 -3 3
-4 -3 -3 -3 -3 -2 -1
fromDown[] = { 0, 1, 4, 1, 1 } => H - n 인덱스에 담음(큰 수부터)
=> { 0, 1, 5, 6, 7 } 위층부터 순서대로

4 4 3 3 3 2 2
fromUp[] = { 0, 2, 3, 2, 0 } => H - n 인덱스에 담음
=> { 0, 2, 5, 7, 7 } 아래층부터 순서대로
 */