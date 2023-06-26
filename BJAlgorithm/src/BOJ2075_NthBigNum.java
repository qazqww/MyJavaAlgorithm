import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2075_NthBigNum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        Stack<Integer>[] stacks = new Stack[N];
        for (int i = 0; i < N; i++) {
            stacks[i] = new Stack<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                stacks[j].add(Integer.parseInt(st.nextToken()));
            }
        }

        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> b.num - a.num);
        for (int i = 0; i < N; i++) {
            pq.offer(new Info(i, stacks[i].pop()));
        }

        for (int i = 0; i < N - 1; i++) {
            int next = pq.poll().no;
            pq.offer(new Info(next, stacks[next].pop()));
        }

        System.out.println(pq.poll().num);
    }

    static class Info {
        int no;
        int num;
        public Info(int no, int num) {
            this.no = no;
            this.num = num;
        }
    }
}