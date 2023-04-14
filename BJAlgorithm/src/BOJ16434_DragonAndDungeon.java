// https://www.acmicpc.net/problem/16434

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16434_DragonAndDungeon {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        long myAtk = Integer.parseInt(st.nextToken());
        int[][] roomInfo = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            roomInfo[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
        }

        long max = 0L;
        long myHp = 0L;
        for (int[] info : roomInfo) {
            int type = info[0];
            int atk = info[1];
            int hp = info[2];

            if (type == 1) {
                long cnt = (hp % myAtk == 0) ? hp / myAtk - 1L : hp / myAtk;
                myHp += atk * cnt;
                max = Math.max(max, myHp);
            }

            else if (type == 2) {
                myAtk += atk;
                myHp -= hp;
                if (myHp < 0) {
                    myHp = 0;
                }
            }
        }

        System.out.println(max + 1L);
    }
}