import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2512_Budget {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int sum = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            pq.offer(temp);
            sum += temp;
        }

        int budget = Integer.parseInt(in.readLine());
        if (sum <= budget) {
            int max = 0;
            while (!pq.isEmpty()) {
                max = pq.poll();
            }
            System.out.println(max);
            return;
        }

        while (true) {
            int avg = budget / pq.size();
            int nextBudget = budget % pq.size();
            boolean hasOk = false;

            while (!pq.isEmpty() && pq.peek() <= avg) {
                hasOk = true;
                int ok = pq.poll();
                nextBudget += (avg - ok);
            }

            if (!hasOk) {
                System.out.println(avg);
                break;
            }
            nextBudget += (avg * pq.size());
            budget = nextBudget;
        }
    }
}
/*
예산 / N 해서
그보다 낮은 애들과의 차이를 +
485 / 4 = 121 + 1
110, 120 => 12 추가
121 * 2 + 1 + 12 = 255

255 / 2 해서
127 + 1
높은애들만 있으면 확정

120 110 125 150 이라 치면

127 + 1 + 2 = 130

 */