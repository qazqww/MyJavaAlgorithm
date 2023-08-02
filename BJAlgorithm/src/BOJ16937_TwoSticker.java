import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16937_TwoSticker {
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (Math.max(h, w) > Math.max(H, W)) {
                continue;
            }
            list.add(new int[] { h, w });
        }

        int answer = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            int nowH = list.get(i)[0];
            int nowW = list.get(i)[1];
            int area = nowH * nowW;
            int area2 = Integer.MIN_VALUE;

            if ((nowH <= H && nowW <= W)) {
                area2 = Math.max(area2, getArea(H, W - nowW, i + 1));
                area2 = Math.max(area2, getArea(H - nowH, W, i + 1));
            }
            if ((nowH <= W && nowW <= H)) {
                area2 = Math.max(area2, getArea(H, W - nowH, i + 1));
                area2 = Math.max(area2, getArea(H - nowW, W, i + 1));
            }
            answer = Math.max(answer, area + area2);
        }
        System.out.println(answer);
    }

    static int getArea(int h, int w, int index) {
        int max = Integer.MIN_VALUE;
        for (int i = index; i < list.size(); i++) {
            int nowH = list.get(i)[0];
            int nowW = list.get(i)[1];
            if ((nowH <= h && nowW <= w) || (nowH <= w && nowW <= h)) {
                max = Math.max(max, nowH * nowW);
            }
        }
        return max;
    }
}