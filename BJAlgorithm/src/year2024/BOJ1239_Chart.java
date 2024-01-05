package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1239_Chart {
    static int[] nums;
    static int[] permu;
    static boolean[] selected;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        nums = new int[N];
        permu = new int[N];
        selected = new boolean[N];
        answer = 0;

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        makePermu(0);
        System.out.println(answer / 2);
    }

    static void makePermu(int index) {
        if (index == N) {
            int cnt = 0;
            int[] newPermu = new int[N * 2];
            for (int i = 0; i < N; i++) {
                newPermu[i] = newPermu[N + i] = permu[i];
            }

            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = i; j < N * 2; j++) {
                    sum += newPermu[j];
                    if (sum >= 50) {
                        if (sum == 50) {
                            cnt++;
                        }
                        break;
                    }
                }
            }
            answer = Math.max(answer, cnt);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                continue;
            }

            permu[index] = nums[i];
            selected[i] = true;
            makePermu(index + 1);
            permu[index] = 0;
            selected[i] = false;
        }
    }
}