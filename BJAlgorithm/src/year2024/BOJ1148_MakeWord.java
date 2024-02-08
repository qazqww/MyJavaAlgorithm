package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ1148_MakeWord {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        ArrayList<Map<Character, Integer>> dict = new ArrayList<>();
        while (true) {
            String str = in.readLine();
            Map<Character, Integer> map = new HashMap<>();
            for (char ch : str.toCharArray()) {
                if (map.containsKey(ch)) {
                    map.put(ch, map.get(ch) + 1);
                }
                else {
                    map.put(ch, 1);
                }
            }
            dict.add(map);
            if (str.equals("-")) {
                break;
            }
        }
        while (true) {
            String str = in.readLine();
            if (str.equals("#")) {
                break;
            }
            char[] newStr = str.toCharArray();
            Arrays.sort(newStr);
            Map<Character, Integer> puzzle = new HashMap<>();
            int[] alphabetCnt = new int[26];
            // 퍼즐판 분석
            for (char ch : newStr) {
                if (puzzle.containsKey(ch)) {
                    puzzle.put(ch, puzzle.get(ch) + 1);
                }
                else {
                    puzzle.put(ch, 1);
                }
            }
            // 사전과 비교
            loop: for (Map<Character, Integer> word : dict) {
                for (char key : word.keySet()) {
                    if (!puzzle.containsKey(key) || puzzle.get(key) < word.get(key)) {
                        continue loop;
                    }
                }
                // 만들 수 있는 문자
                for (char key : word.keySet()) {
                    alphabetCnt[key - 'A']++;
                }
            }
            // 쓰인 알파벳 카운트
            int max = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 26; i++) {
                if (puzzle.containsKey((char)(i + 'A'))) {
                    max = Math.max(max, alphabetCnt[i]);
                    min = Math.min(min, alphabetCnt[i]);
                }
            }
            for (int i = 0; i < 26; i++) {
                if (alphabetCnt[i] == min && puzzle.containsKey((char)(i + 'A'))) {
                    answer.append((char)(i + 'A'));
                }
            }
            answer.append(" " + min + " ");
            for (int i = 0; i < 26; i++) {
                if (alphabetCnt[i] == max && puzzle.containsKey((char)(i + 'A'))) {
                    answer.append((char)(i + 'A'));
                }
            }
            answer.append(" " + max + "\n");
        }
        answer.setLength(answer.length() - 1);
        System.out.println(answer);
    }
}