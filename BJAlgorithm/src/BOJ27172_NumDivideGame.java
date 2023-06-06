import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ27172_NumDivideGame {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[][] nums = new int[N][2]; // 카드 숫자, 점수
        Map<Integer, Integer> map = new HashMap<>(); // 카드 숫자, 인덱스
        int max = 0;

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = new int[] { num, 0 };
            map.put(num, i);
            max = Math.max(max, num);
        }

        for (int i = 0; i < N; i++) {
            int num = nums[i][0];
            while (num <= max) {
                num += nums[i][0];
                if (map.containsKey(num)) {
                    nums[i][1]++;
                    nums[map.get(num)][1]--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(nums[i][1] + " ");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}