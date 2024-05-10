package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15966_ManyChickenOneCrane {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] nums = new int[1_000_001];
        StringTokenizer st = new StringTokenizer(in.readLine());
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if (nums[num - 1] == 0) {
                nums[num] = 1;
            }
            else if (nums[num - 1] + 1 > nums[num]) {
                nums[num] = nums[num - 1] + 1;
            }
        }

        int answer = -1;
        for (int i = 0; i < nums.length; i++) {
            answer = Math.max(answer, nums[i]);
        }
        System.out.println(answer);
    }
}