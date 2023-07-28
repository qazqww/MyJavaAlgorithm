import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1052_WaterBottle {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());
        List<Integer> bottle = new ArrayList<>();

        int step = 0;
        while (N > 1) {
            if (N % 2 == 1) {
                bottle.add((int)Math.pow(2, step));
            }
            N /= 2;
            step++;
        }
        bottle.add((int)Math.pow(2, step));

        int answer = 0;
        for (int i = 0; i < bottle.size() - (limit - 1) - 1; i++) {
            int num = bottle.get(i);
            if (i > 0) {
                num *= 2;
            }
            while (num < bottle.get(i + 1)) {
                answer += num;
                num *= 2;
            }
        }
        System.out.println(answer < 0 ? -1 : answer);
    }
}