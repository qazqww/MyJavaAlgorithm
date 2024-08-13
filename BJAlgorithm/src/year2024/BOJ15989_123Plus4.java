package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15989_123Plus4 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        int[] arr = new int[10001];
        arr[1] = 1;
        for (int i = 2; i <= 10000; i++) {
            int two = 0;
            int newNum = 0;
            while (true) {
                int remained = i - 2 * two;
                if (remained < 0) {
                    break;
                }
                if (remained % 3 == 0) {
                    newNum++;
                }
                two++;
            }
            arr[i] = arr[i - 1] + newNum;
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(in.readLine());
            sb.append(arr[num]).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}
  /*
       1 : 1 0 0
       2 : 2 0 0, 0 1 0
       3 : 3 0 0, 1 1 0, 0 0 1
       4 : 4 0 0, 2 1 0, 1 0 1, 0 2 0
       5 : 5 0 0, 3 1 0, 2 0 1, 1 2 0, 0 1 1
       6 : 6 0 0, 4 1 0, 3 0 1, 2 2 0, 1 1 1, 0 3 0, 0 0 2
       7 : 7 0 0, 5 1 0, 4 0 1, 3 2 0, 2 1 1, 1 3 0, 1 0 2, 0 2 1
       8 : 8 0 0, 6 1 0, 5 0 1, 4 2 0, 3 1 1, 2 3 0, 2 0 2, 1 2 1, 0 4 0, 0 1 2
       9 : 8 0 0, 6 1 0, 5 0 1, 4 2 0, 3 1 1, 2 3 0, 2 0 2, 1 2 1, 0 4 0, 0 1 2, 0 3 1, 0 0 3
       10 : 8 0 0, 6 1 0, 5 0 1, 4 2 0, 3 1 1, 2 3 0, 2 0 2, 1 2 1, 0 4 0, 0 1 2, 0 3 1, 0 0 3, 0 5 0, 0 2 2
       11 : 8 0 0, 6 1 0, 5 0 1, 4 2 0, 3 1 1, 2 3 0, 2 0 2, 1 2 1, 0 4 0, 0 1 2, 0 3 1, 0 0 3, 0 5 0, 0 2 2, 0 4 1, 0 1 3
       12 : 8 0 0, 6 1 0, 5 0 1, 4 2 0, 3 1 1, 2 3 0, 2 0 2, 1 2 1, 0 4 0, 0 1 2, 0 3 1, 0 0 3, 0 5 0, 0 2 2, 0 4 1, 0 1 3, 0 6 0, 0 3 2, 0 0 4
       1이 들어가지 않은걸로 수를 만들 수 있는가
       2*n + 3*m의 형태 (n>=0, m>=0)
         */