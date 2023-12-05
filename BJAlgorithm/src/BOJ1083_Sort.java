import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1083_Sort {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int S = Integer.parseInt(in.readLine());
        int cnt = S;
        int target = 0;

        while (cnt > 0 && target < N) {
            int maxRange = -1;
            if (cnt >= N - target) {
                maxRange = N;
            }
            else {
                maxRange = target + cnt + 1;
            }

            // 가능한 범위 내에서 가장 큰 수를 찾는다
            int biggest = -1;
            int index = -1;
            for (int i = target; i < maxRange; i++) {
                if (arr[i] > biggest) {
                    biggest = arr[i];
                    index = i;
                }
            }
            if (index == target) {
                target++;
                continue;
            }
            swap(arr, index, target);
            cnt -= index - target;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i] + " ");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void swap(int[] arr, int startIdx, int targetIdx) {
        int temp = arr[startIdx];
        for (int i = startIdx; i > targetIdx; i--) {
            arr[i] = arr[i - 1];
        }
        arr[targetIdx] = temp;
    }
}