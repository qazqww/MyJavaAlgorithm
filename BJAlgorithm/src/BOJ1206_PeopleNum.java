import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1206_PeopleNum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            String[] temp = in.readLine().split("\\.");
            values[i] = Integer.parseInt(temp[1]);
        }

        int num = 1;
        while (num < 1000) {
            boolean isPossible = true;
            for (int i = 0; i < N; i++) {
                int result = values[i] * num;
                int result2 = (values[i] + 1) * num;
                int temp = result2 / 1000 * 1000;

                if (temp < result || temp >= result2) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                break;
            }
            num++;
        }
        System.out.println(num);
    }
}