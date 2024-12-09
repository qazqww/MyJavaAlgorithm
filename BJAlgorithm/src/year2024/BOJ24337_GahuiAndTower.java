package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ24337_GahuiAndTower {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a + b - 1 > N) {
            System.out.println(-1);
            return;
        }

        StringBuilder asc = new StringBuilder();
        StringBuilder desc = new StringBuilder();
        if (a + b - 1 < N && a == 1) {
            for (int i = b - 1; i >= 1; i--) {
                desc.append(i).append(" ");
            }
        }
        else if (a >= b) {
            for (int i = 1; i <= a; i++) {
                asc.append(i).append(" ");
            }
            for (int i = b - 1; i >= 1; i--) {
                desc.append(i).append(" ");
            }
        }
        else {
            for (int i = 1; i <= a - 1; i++) {
                asc.append(i).append(" ");
            }
            for (int i = b; i >= 1; i--) {
                desc.append(i).append(" ");
            }
        }
        asc.setLength(Math.max(0, asc.length() - 1));
        desc.setLength(Math.max(0, desc.length() - 1));

        StringBuilder others = new StringBuilder();
        others.append("1 ".repeat(Math.max(0, N - (a + b - 1))));
        if (others.length() > 0) {
            others.setLength(others.length() - 1);
        }

        StringBuilder answer = new StringBuilder();
        if (a + b - 1 < N && a == 1) {
            answer.append(b).append(" ");
            if (others.length() > 0) {
                answer.append(others).append(" ");
            }
            answer.append(desc);
        }
        else {
            if (others.length() > 0) {
                answer.append(others).append(" ");
            }
            if (asc.length() > 0) {
                answer.append(asc).append(" ");
            }
            answer.append(desc);
        }
        if (answer.charAt(answer.length() - 1) == ' ') {
            answer.setLength(answer.length() - 1);
        }
        System.out.println(answer);
    }
}
/*
5 1 5
5 4 3 2 1

5 1 3
3 1 1 2 1



 */