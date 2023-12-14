import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1599_Minsik {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] order = new int[] { 1, 2, 12, 4, 5, 0, 6, 7, 8, 0, 3, 9, 10, 11, 13, 14, 0, 15, 16, 17, 18, 0, 19, 0, 20, 0 };

        PriorityQueue<String[]> pq = new PriorityQueue<>((a, b) -> {
            for (int i = 0; i < Math.min(a[1].length(), b[1].length()); i++) {
                if (order[a[1].charAt(i) - 'a'] < order[b[1].charAt(i) - 'a']) {
                    return -1;
                }
                else if (order[a[1].charAt(i) - 'a'] > order[b[1].charAt(i) - 'a']) {
                    return 1;
                }
            }
            if (a[1].length() < b[1].length()) {
                return -1;
            }
            else {
                return 1;
            }
        });

        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            String newStr = str.replace("ng", "c");
            pq.offer(new String[] { str, newStr });
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.poll()[0]);
        }
    }
}