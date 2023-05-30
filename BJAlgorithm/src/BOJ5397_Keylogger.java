import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5397_Keylogger {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for (int t = 0; t < T; t++) {
            String str = in.readLine();
            int start = 0;
            while (str.charAt(start) == '<' || str.charAt(start) == '>' || str.charAt(start) == '-') {
                start++;
            }
            Node head = new Node((char)0, null);
            Node tail = new Node((char)0, head);
            head.next = tail;
            Node now = head;

            for (int i = start; i < str.length(); i++) {
                char ch = str.charAt(i);
                switch (ch) {
                    case '<':
                        if (now.prev != null) {
                            now = now.prev;
                        }
                        break;
                    case '>':
                        if (now.next != null && now.next.ch != 0) {
                            now = now.next;
                        }
                        break;
                    case '-':
                        if (now.ch == 0) {
                            continue;
                        }
                        Node nextTemp = now.next;
                        now = now.prev;
//                        now.next.prev = null;
//                        now.next.next = null;
                        nextTemp.prev = now;
                        now.next = nextTemp;
                        break;
                    default:
                        Node newNode = new Node(ch, now);
                        newNode.next = now.next;
                        now.next.prev = newNode;
                        now.next = newNode;
                        now = now.next;
                        break;
                }
            }

            StringBuilder sb = new StringBuilder();
            now = head.next;
            while (now.ch != 0) {
                sb.append(now.ch);
                now = now.next;
            }
            System.out.println(sb);
        }
    }

    static class Node {
        char ch;
        Node prev;
        Node next;

        public Node(char ch, Node prev) {
            this.ch = ch;
            this.prev = prev;
            this.next = null;
        }
    }
}