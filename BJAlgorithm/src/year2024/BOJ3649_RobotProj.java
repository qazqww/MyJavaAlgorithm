package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3649_RobotProj {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = in.readLine()) != null) {
            if (str.isEmpty()) {
                break;
            }
            int x = Integer.parseInt(str) * 10_000_000;
            int n = Integer.parseInt(in.readLine());
            int[] lego = new int[n];
            for (int i = 0; i < n; i++) {
                lego[i] = Integer.parseInt(in.readLine());
            }

            Arrays.sort(lego);
            int start = 0;
            int end = lego.length - 1;
            boolean isFound = false;
            while (start < end) {
                int sum = lego[start] + lego[end];
                if (sum == x) {
                    System.out.printf("yes %d %d\n", lego[start], lego[end]);
                    isFound = true;
                    break;
                } else if (sum > x) {
                    end--;
                } else {
                    start++;
                }
            }
            if (!isFound) {
                System.out.println("danger");
            }
        }
    }
}