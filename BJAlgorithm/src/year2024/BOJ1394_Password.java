package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ1394_Password {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = in.readLine().toCharArray();
        Map<Character, Integer> findIndex = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            findIndex.put(chars[i], i);
        }
        String pw = in.readLine();
        long answer = findIndex.get(pw.charAt(0)) + 1;
        for (int i = 1; i < pw.length(); i++) {
            answer = answer * chars.length + findIndex.get(pw.charAt(i)) + 1;
            answer %= 900_528;
        }
        System.out.println(answer);
    }
}