import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1446_Shortcut {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[] totalDist = new int[D + 1];
        for (int i = 0; i < D + 1; i++) {
            totalDist[i] = i;
        }

        PriorityQueue<Shortcut> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (end > D) {
                continue;
            }

            pq.offer(new Shortcut(start, end, dist));
        }

        while (!pq.isEmpty()) {
            Shortcut sc = pq.poll();
            if (totalDist[sc.end] > totalDist[sc.start] + sc.dist) {
                totalDist[sc.end] = totalDist[sc.start] + sc.dist;
                for (int i = sc.end + 1; i < D + 1; i++) {
                    if (totalDist[i] < totalDist[sc.end] + i - sc.end) {
                        break;
                    }
                    totalDist[i] = totalDist[sc.end] + i - sc.end;
                }
            }
        }
        System.out.println(totalDist[D]);
    }

    static class Shortcut {
        int start;
        int end;
        int dist;

        public Shortcut(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }
    }
}