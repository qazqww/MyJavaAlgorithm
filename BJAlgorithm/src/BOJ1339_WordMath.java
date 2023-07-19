import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BOJ1339_WordMath {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String word = in.readLine();

            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                if (!map.containsKey(ch)) {
                    map.put(ch, 0);
                }
                map.put(ch, map.get(ch) + (int)Math.pow(10, word.length() - j - 1));
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (char ch : map.keySet()) {
            pq.offer(map.get(ch));
        }

        int result = 0;
        int num = 9;
        while (!pq.isEmpty()) {
            result += pq.poll() * num--;
        }
        System.out.println(result);
    }
}