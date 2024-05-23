package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ12869_Mutalisk {
    static int answer = 1_000_000;
    static Set<String> checked = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        String[] temp = in.readLine().split(" ");
        int[] scv = new int[3];
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(temp[i]);
        }
        attack(scv, 0);
        System.out.println(answer);
    }

    static void attack(int[] scv, int cnt) {
        if (scv[0] <= 0 && scv[1] <= 0 && scv[2] <= 0) {
            answer = Math.min(answer, cnt);
            return;
        }

        Arrays.sort(scv);
        String str = arrToStr(scv);
        if (checked.contains(str)) {
            return;
        }
        checked.add(str);

        if (scv[0] >= 0) {
            attack(new int[]{scv[0] - 9, scv[1] - 3, scv[2] - 1}, cnt + 1);
            attack(new int[]{scv[0] - 9, scv[1] - 1, scv[2] - 3}, cnt + 1);
        }
        if (scv[1] >= 0) {
            attack(new int[]{scv[0] - 3, scv[1] - 9, scv[2] - 1}, cnt + 1);
            attack(new int[]{scv[0] - 1, scv[1] - 9, scv[2] - 3}, cnt + 1);
        }
        if (scv[2] >= 0) {
            attack(new int[]{scv[0] - 3, scv[1] - 1, scv[2] - 9}, cnt + 1);
            attack(new int[]{scv[0] - 1, scv[1] - 3, scv[2] - 9}, cnt + 1);
        }
    }

    static String arrToStr(int[] scv) {
        return "" + scv[0] + scv[1] + scv[2];
    }
}