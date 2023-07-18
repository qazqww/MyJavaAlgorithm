package B5toS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ10814_SortByAge {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(in.readLine());
        PriorityQueue<Member> pq = new PriorityQueue<>((a, b) -> {
            if (a.age != b.age)
                return a.age - b.age;
            return a.idx - b.idx;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            pq.offer(new Member(i, Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Member m = pq.poll();
            sb.append(m.age + " " + m.name + "\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class Member {
        int idx;
        int age;
        String name;
        public Member(int idx, int age, String name) {
            this.idx = idx;
            this.age = age;
            this.name = name;
        }
    }
}
