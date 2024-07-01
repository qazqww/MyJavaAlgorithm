package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1019_BookPage {
    static int[][] data = new int[10][10];

    public static void main(String[] args) throws IOException {
        setup();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] answer = new int[10];
        String str = Integer.toString(N);
        int numOfDigit = str.length();
        int unitNum = 0;

        for (char ch : str.toCharArray()) { // 첫 자리부터 하나씩 탐색
            int num = ch - '0';
            // 1. data[numOfDigit - 1]을 num번
            if (numOfDigit > 1) {
                for (int j = 0; j < 10; j++) {
                    answer[j] += data[numOfDigit - 2][j] * num;
                }
            }
            // 2. 앞자리 반복 가산
            int cnt = ((int) Math.pow(10, numOfDigit - 1) - 1);
            for (int i = 0; i <= str.length() - numOfDigit; i++) {
                if (i == str.length() - numOfDigit) {
                    int start = numOfDigit == str.length() ? 1 : 0;
                    for (int j = start; j < num; j++) {
                        answer[j] += cnt;
                    }
                }
                else {
                    answer[str.charAt(i) - '0'] += cnt * num;
                }
            }
            // 3. 단위수 계산
            int unit = (int) Math.pow(10, numOfDigit - 1);
            for (int i = 0; i < num; i++) {
                unitNum += unit;
                int[] nums = getNums(unitNum);
                for (int j = 0; j < 10; j++) {
                    answer[j] += nums[j];
                }
            }
            numOfDigit--;
        }
        for (int i = 100; i <= 1_000_000_000; i *= 10) {
            if (N >= i) {
                answer[0] -= i / 10 - 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(answer[i] + " ");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static int[] getNums(int num) {
        int[] nums = new int[10];
        String str = Integer.toString(num);
        for (char ch : str.toCharArray()) {
            nums[ch - '0']++;
        }
        return nums;
    }

    static void setup() {
        int[] zeros = new int[] { 0, 18, 297, 3996, 49995, 599994, 6999993, 79999992, 899999991 };
        int[] others = new int[] { 1, 20, 300, 4000, 50000, 600000, 7000000, 80000000, 900000000 };
        for (int i = 0; i < 9; i++) {
            Arrays.fill(data[i], others[i]);
            data[i][0] = zeros[i];
        }
    }
}