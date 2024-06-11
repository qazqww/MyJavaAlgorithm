package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2143_TwoArraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        int N = Integer.parseInt(in.readLine());
        int[] arr1 = new int[N + 1];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr1[i] = arr1[i - 1] + Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(in.readLine());
        int[] arr2 = new int[M + 1];
        st = new StringTokenizer(in.readLine());
        for (int i = 1; i < M + 1; i++) {
            arr2[i] = arr2[i - 1] + Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < i; j++) {
                int result = arr1[i] - arr1[j];
                if (map.containsKey(result)) {
                    map.put(result, map.get(result) + 1);
                }
                else {
                    map.put(result, 1);
                }
            }
        }
        int[][] subArr1 = new int[map.size()][2];
        int idx = 0;
        for (int num : map.keySet()) {
            subArr1[idx][0] = num;
            subArr1[idx++][1] = map.get(num);
        }

        map = new HashMap<>();
        for (int i = 1; i < M + 1; i++) {
            for (int j = 0; j < i; j++) {
                int result = arr2[i] - arr2[j];
                if (map.containsKey(result)) {
                    map.put(result, map.get(result) + 1);
                }
                else {
                    map.put(result, 1);
                }
            }
        }
        int[][] subArr2 = new int[map.size()][2];
        idx = 0;
        for (int num : map.keySet()) {
            subArr2[idx][0] = num;
            subArr2[idx++][1] = map.get(num);
        }
        Arrays.sort(subArr1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(subArr2, Comparator.comparingInt(a -> a[0]));

        int idx1 = 0;
        int idx2 = subArr2.length - 1;
        long answer = 0;
        while (idx1 < subArr1.length && idx2 >= 0) {
            int sum = subArr1[idx1][0] + subArr2[idx2][0];
            if (sum == T) {
                answer += (long)subArr1[idx1][1] * subArr2[idx2][1];
                idx1++;
                idx2--;
            }
            else if (sum < T) {
                idx1++;
            }
            else {
                idx2--;
            }
        }

        System.out.println(answer);
    }
}