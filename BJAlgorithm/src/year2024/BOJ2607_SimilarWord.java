package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2607_SimilarWord {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] origin = new int[26];
        String originStr = in.readLine();
        for (int i = 0; i < originStr.length(); i++) {
            origin[originStr.charAt(i) - 'A']++;
        }

        int answer = 0;
        loop: for (int i = 1; i < N; i++) {
            String targetStr = in.readLine();
            if (Math.abs(originStr.length() - targetStr.length()) > 1) {
                continue;
            }
            int[] target = new int[26];
            for (int j = 0; j < targetStr.length(); j++) {
                target[targetStr.charAt(j) - 'A']++;
            }

            int gap = 0;
            for (int j = 0; j < 26; j++) {
                gap += Math.abs(origin[j] - target[j]);
                if (gap > 2) {
                    continue loop;
                }
            }
            answer++;
        }
        System.out.println(answer);
    }
}