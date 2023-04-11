import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ6603_Lotto {
    static int[] numArr = new int[0];
    static boolean[] selected = new boolean[0];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());

            if (k == 0) {
                break;
            }

            int[] nums = new int[k];
            for (int i = 0; i < k; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            numArr = Arrays.copyOf(nums, nums.length);
            selected = new boolean[k];
            subset(0, 6);
            sb.append("\n");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        System.out.println(sb);
    }

    static void subset(int index, int cnt) {
        if (index == selected.length) {
            if (cnt == 0) {
                for (int i = 0; i < selected.length; i++) {
                    if (selected[i]) {
                        sb.append(numArr[i] + " ");
                    }
                }
                sb.setLength(sb.length() - 1);
                sb.append("\n");
            }
            return;
        }

        if (cnt > 0) {
            selected[index] = true;
            subset(index + 1, cnt - 1);
        }
        selected[index] = false;
        subset(index + 1, cnt);
    }
}