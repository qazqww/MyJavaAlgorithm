import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5397_Keylogger_Failed {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for (int t = 0; t < T; t++) {
            String str = in.readLine();
            StringBuilder sb = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            int cursor = 0;
            int cnt = 0;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                char next = 0;
                if (i != str.length() - 1) {
                    next = str.charAt(i + 1);
                }

                switch (ch) {
                    case '<':
                        cursor = (cursor > 0) ? cursor - 1 : cursor;
                        break;
                    case '>':
                        cursor = (cursor < sb.length()) ? cursor + 1 : cursor;
                        break;
                    case '-':
                        cnt++;
                        if (next != '-') {
                            int start = (cnt > cursor) ? 0 : cursor - cnt;
                            sb = sb.delete(start, cursor);
                            cursor = start;
                            cnt = 0;
                        }
                        break;
                    default:
                        temp.append(ch);
                        if (next == '<' || next == '>' || next == '-' || next == 0) {
                            sb.insert(cursor, temp);
                            cursor += temp.length();
                            temp = new StringBuilder();
                        }
                        break;
                }
            }
            System.out.println(sb);
        }
    }
}