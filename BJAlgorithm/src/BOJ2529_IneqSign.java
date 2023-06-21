import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2529_IneqSign {
    static int N;
    static long min, max;
    static String minStr, maxStr;
    static char[] ineq;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        ineq = new char[N];
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
        minStr = "";
        maxStr = "";
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            ineq[i] = st.nextToken().charAt(0);
        }

        permu(0, new boolean[10]);
        System.out.println(maxStr);
        System.out.println(minStr);
    }

    static void permu(int index, boolean[] visited) {
        if (index == N + 1) {
            long num = Long.parseLong(sb.toString());
            if (min > num) {
                min = num;
                minStr = sb.toString();
            }
            if (max < num) {
                max = num;
                maxStr = sb.toString();
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) {
                continue;
            }

            if (index != 0) {
                switch (ineq[index - 1]) {
                    case '<':
                        if (sb.charAt(index - 1) - '0' >= i) {
                            continue;
                        }
                        break;
                    case '>':
                        if (sb.charAt(index - 1) - '0' <= i) {
                            continue;
                        }
                        break;
                }
            }

            visited[i] = true;
            sb.append(i);
            permu(index + 1, visited);
            sb.setLength(sb.length() - 1);
            visited[i] = false;
        }
    }
}