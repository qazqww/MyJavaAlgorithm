package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16472_Cat {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        String str = in.readLine();

        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int answer = 0;

        for (int i = 0; i < str.length(); i++) {
            if (!map.containsKey(str.charAt(i))) {
                if (map.size() >= N) {
                    char erased = getErasedAlphabet(map);
                    start = map.get(erased) + 1;
                    map.remove(erased);
                }
            }
            answer = Math.max(answer, i - start + 1);
            map.put(str.charAt(i), i);
        }
        System.out.println(answer);
    }

    static char getErasedAlphabet(Map<Character, Integer> map) {
        char erased = ' ';
        int order = Integer.MAX_VALUE;
        for (char key : map.keySet()) {
            if (map.get(key) < order) {
                erased = key;
                order = map.get(key);
            }
        }
        return erased;
    }
}