import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1276_PLATFORME {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] highest = new int[10000];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            pq.offer(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1 });
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] plat = pq.poll();
            answer += (plat[0] - highest[plat[1]]) + (plat[0] - highest[plat[2]]);
            for (int i = plat[1]; i <= plat[2]; i++) {
                highest[i] = plat[0];
            }
        }
        System.out.println(answer);
    }
}