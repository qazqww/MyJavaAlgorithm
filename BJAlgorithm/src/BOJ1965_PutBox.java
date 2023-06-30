import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1965_PutBox {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] arr = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int[] put = new int[max + 1];
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            max = -1;
            for (int j = 0; j < num; j++) {
                max = Math.max(max, put[j]);
            }
            put[num] = max + 1;
        }

        max = 0;
        for (int i = 0; i < put.length; i++) {
            max = Math.max(max, put[i]);
        }

        System.out.println(max);
    }
}