package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16457_MapleStory {
    static int N, M, K, answer;
    static int[] quest;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        quest = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int questNum = 0;
            while (st.hasMoreTokens()) {
                questNum |= (1 << Integer.parseInt(st.nextToken()));
            }
            quest[i] = questNum;
        }
        combi(1, 0, 0);
        System.out.println(answer);
    }

    static void combi(int num, int index, int selected) {
        if (index == N) {
            int cnt = 0;
            for (int q : quest) {
                if ((selected | q) == selected) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
            return;
        }
        for (int i = num; i <= N * 2; i++) {
            combi(i + 1, index + 1, selected | (1 << i));
        }
    }
}