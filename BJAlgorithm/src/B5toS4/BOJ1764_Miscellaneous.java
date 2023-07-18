package B5toS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class BOJ1764_Miscellaneous {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = in.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(in.readLine());
        }

        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            String str = in.readLine();
            if (set.contains(str)) {
                pq.offer(str);
            }
        }

        System.out.println(pq.size());
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
