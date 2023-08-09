import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ12789_DokiDokiSnack {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());

        int target = 1;
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(st.nextToken()));
        }

        while (!queue.isEmpty() || !stack.isEmpty()) {
            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                target++;
            }
            else {
                while (!queue.isEmpty()) {
                    if (queue.peek() == target) {
                        queue.poll();
                        target++;
                        break;
                    }
                    else {
                        stack.push(queue.poll());
                    }
                }
            }
            if (queue.isEmpty() && (!stack.isEmpty() && stack.peek() != target)) {
                System.out.println("Sad");
                return;
            }
        }

        System.out.println("Nice");
    }
}