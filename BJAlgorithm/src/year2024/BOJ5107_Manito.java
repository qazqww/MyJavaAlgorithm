package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ5107_Manito {
    static Map<String, String> map;
    static Map<String, Boolean> visited;
    static int answer = 0;
    static int cnt = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        map = new HashMap<>();
        visited = new HashMap<>();
        while (true) {
            String str = in.readLine();
            if (str.charAt(0) == '0') {
                check();
                return;
            }
            else if (str.charAt(0) > '0' && str.charAt(0) <= '9') {
                check();
                map = new HashMap<>();
                int N = Integer.parseInt(str);
            }
            else {
                String[] temp = str.split(" ");
                map.put(temp[0], temp[1]);
                visited.put(temp[0], false);
            }
        }
    }

    static void check() {
        cnt++;
        if (map.isEmpty()) {
            return;
        }
        answer = 0;
        for (String key : map.keySet()) {
            if (visited.get(key)) {
                continue;
            }

            visited.put(key, true);
            String next = map.get(key);
            while (!visited.get(next)) {
                visited.put(next, true);
                next = map.get(next);
            }
            answer++;
        }
        System.out.println(cnt + " " + answer);
    }
}