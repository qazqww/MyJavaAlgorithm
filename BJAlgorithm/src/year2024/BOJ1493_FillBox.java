package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1493_FillBox {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        long length = Long.parseLong(st.nextToken());
        long width = Long.parseLong(st.nextToken());
        long height = Long.parseLong(st.nextToken());
        long size = length * width * height; // 큐브의 총 부피

        int N = Integer.parseInt(in.readLine());
        int[] cnt = new int[20]; // 주어진 상자의 개수
        long[] canUse = new long[20]; // 큐브에 채울 수 있는 각 상자의 최대 개수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            cnt[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        // 각 상자만으로 큐브를 채울 때 최대 개수를 구한다
        for (int i = 0; i < 20; i++) {
            long unit = (long) Math.pow(2, i);
            canUse[i] = (length / unit) * (width / unit) * (height / unit);
        }

        long answer = 0; // 사용한 상자 개수
        long filled = 0; // 상자로 채워진 부피값

        // 큰 상자부터 채워나간다
        for (int i = 19; i >= 0; i--) {

            // 현재 큐브에 넣을 수 있는 상자의 개수를 구한 뒤, 큐브에 넣는다
            long used = Math.min(canUse[i], cnt[i]);
            answer += used;

            // 큐브가 채워진만큼 다른 상자들이 들어갈 공간이 줄어듦을 계산한다
            long k = 8;
            for (int j = i - 1; j >= 0; j--, k *= 8) {
                canUse[j] -= used * k;
            }

            // 이번 상자로 채워진 부피값을 더한다.
            filled += (long) (used * Math.pow(Math.pow(2, i), 3));
        }

        // 큐브를 모두 채웠는지 판단하여 결과를 출력한다
        System.out.println(size != filled ? -1 : answer);
    }
}

/*
37 x 42 x 59
1x1x1 => 91686 -(32)32*32*32*1 -4096*4 -512*44 -64*140 -8*882 = 3990
               -32768 -16384 -22528 -8960 -7056
2x2x2 => 10962 -(32)16*16*16*1 -(16)8*8*8*4 -(8)4*4*4*44 -(4)2*2*2*140 = 882
               -4096 -2048 -2816 -1120
4x4x4 => 1260 -352 -256 -512 = 140
8x8x8 => 140 -32 -64 = 44
16x16x16 => 12 -8 = 4
32x32x32 => 1
 */