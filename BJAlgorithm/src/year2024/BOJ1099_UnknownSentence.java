package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1099_UnknownSentence {
    static int len = -1;
    static int answer = Integer.MAX_VALUE;
    static String str;
    static int[] sums;
    static int[] record;
    static ArrayList<Word> wordList;
    static Queue<int[]> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = in.readLine();
        len = str.length();
        sums = new int[len + 1];
        record = new int[len + 1];
        Arrays.fill(record, 1_000_000);
        for (int i = 1; i < len + 1; i++) {
            sums[i] = sums[i - 1] + str.charAt(i - 1) - 'a' + 1;
        }

        int N = Integer.parseInt(in.readLine());
        wordList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            wordList.add(new Word(in.readLine()));
        }
        queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            check(arr[0], arr[1]);
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void check(int index, int value) {
        if (index == len) {
            answer = Math.min(answer, value);
            return;
        }
        for (Word word : wordList) {
            int wordLen = word.original.length();
            if (index + wordLen > str.length()) {
                continue;
            }
            int sum = sums[index + wordLen] - sums[index];
            if (word.sum == sum) {
                String part = str.substring(index, index + wordLen);
                int valueCnt = compareWord(part, word.original);
                if (valueCnt < 0) {
                    continue;
                }
                if (value + valueCnt < record[index + wordLen]) {
                    record[index + wordLen] = value + valueCnt;
                    queue.offer(new int[] { index + wordLen, value + valueCnt });
                }
            }
        }
    }

    static int compareWord(String a, String b) {
        if (a.length() != b.length()) {
            return -1;
        }
        int valueCnt = 0;
        int[] aCnt = new int[26];
        int[] bCnt = new int[26];
        for (int i = 0; i < a.length(); i++) {
            aCnt[a.charAt(i) - 'a']++;
            bCnt[b.charAt(i) - 'a']++;
            if (a.charAt(i) != b.charAt(i)) {
                valueCnt++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (aCnt[i] != bCnt[i]) {
                return -1;
            }
        }
        return valueCnt;
    }

    static class Word {
        int sum = 0;
        String original;

        public Word(String original) {
            this.original = original;
            for (char ch : original.toCharArray()) {
                sum += ch - 'a' + 1;
            }
        }
    }
}