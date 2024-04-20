package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2346_BalloonBomb {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        Node head = new Node(1, null, null, Integer.parseInt(st.nextToken()));
        Node cur = head;
        for (int i = 1; i < N; i++) {
            Node newNode = new Node(i + 1, cur, null, Integer.parseInt(st.nextToken()));
            cur.right = newNode;
            cur = newNode;
            if (i == N - 1) {
                cur.right = head;
                head.left = cur;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(head.no + " ");
            head = head.deleteNode();
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static class Node {
        int no;
        Node left;
        Node right;
        int num;

        public Node(int no, Node left, Node right, int num) {
            this.no = no;
            this.left = left;
            this.right = right;
            this.num = num;
        }

        Node deleteNode() {
            left.right = right;
            right.left = left;
            Node cur = this;
            if (num > 0) {
                for (int i = 0; i < num; i++) {
                    cur = cur.right;
                }
            }
            else {
                for (int i = 0; i < -num; i++) {
                    cur = cur.left;
                }
            }
            return cur;
        }
    }
}