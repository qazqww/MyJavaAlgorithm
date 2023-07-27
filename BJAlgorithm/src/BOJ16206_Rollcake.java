import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16206_Rollcake {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int maxCnt = Integer.parseInt(st.nextToken());
        int[] len = new int[N];

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            len[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
           if (a % 10 == 0 && b % 10 != 0) {
               return -1;
           }
           else if (a % 10 != 0 && b % 10 == 0) {
               return 1;
           }
           else return a - b;
        });

        int ten = 0;
        for (int i = 0; i < N; i++) {
            if (len[i] == 10) {
                ten++;
                continue;
            }
            else if (len[i] < 10) {
                continue;
            }
            pq.offer(len[i]);
        }

        int cnt = 0;
        while (cnt < maxCnt && !pq.isEmpty()) {
            int num = pq.poll();
            num -= 10;
            ten++;
            if (num == 10) {
                ten++;
            }
            else if (num > 10) {
                pq.offer(num);
            }
            cnt++;
        }

        System.out.println(ten);
    }
}