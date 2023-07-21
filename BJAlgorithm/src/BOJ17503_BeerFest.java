import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17503_BeerFest {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int days = Integer.parseInt(st.nextToken());
        int require = Integer.parseInt(st.nextToken());
        int beers = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < beers; i++) {
            st = new StringTokenizer(in.readLine());
            int like = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            pq.offer(new int[] { like, level });
        }

        int nowLevel = 0;
        int nowReq = 0;
        PriorityQueue<Integer> meet = new PriorityQueue<>();
        for (int i = 0; i < days; i++) {
            int[] beer = pq.poll();
            meet.offer(beer[0]);
            nowReq += beer[0];
            nowLevel = beer[1];
        }

        while (nowReq < require && !pq.isEmpty()) {
            int[] beer = pq.poll();
            nowReq -= meet.poll();
            meet.offer(beer[0]);
            nowReq += beer[0];
            nowLevel = beer[1];
        }

        if (nowReq < require) {
            System.out.println(-1);
        }
        else {
            System.out.println(nowLevel);
        }
    }
}