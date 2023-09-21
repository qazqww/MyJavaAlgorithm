import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2170_DrawLine {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<int[]> list = new ArrayList<>();

        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new int[] { start, end });
        }
        Collections.sort(list, Comparator.comparing(a -> a[0]));

        int end = list.get(0)[1];
        int answer = end - list.get(0)[0];

        for (int i = 1; i < list.size(); i++) {
            int[] cur = list.get(i);
            if (cur[0] > end) {
                answer += cur[1] - cur[0];
                end = cur[1];
            }
            else if (cur[1] > end) {
                answer += cur[1] - end;
                end = cur[1];
            }
        }
        System.out.println(answer);
    }
}