import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13164_HappyKindergarten {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int first = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());
            pq.offer(next - first);
            first = next;
        }

        for (int i = 0; i < K - 1; i++) {
            pq.poll();
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }
        System.out.println(answer);
    }
}