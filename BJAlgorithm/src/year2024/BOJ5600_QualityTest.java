package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ5600_QualityTest {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int aNum = Integer.parseInt(st.nextToken());
        int bNum = Integer.parseInt(st.nextToken());
        int cNum = Integer.parseInt(st.nextToken());
        int[] caseArr = new int[aNum + bNum + cNum];
        Arrays.fill(caseArr, 2);

        int N = Integer.parseInt(in.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[3] - a[3]);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            pq.offer(new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1
            , Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) });
        }

        while (!pq.isEmpty()) {
            int size = pq.size();
            boolean isValid = false;
            for (int i = 0; i < size; i++) {
                int[] arr = pq.poll();

                if (arr[3] == 1) {
                    caseArr[arr[0]] = 1;
                    caseArr[arr[1]] = 1;
                    caseArr[arr[2]] = 1;
                    isValid = true;
                }
                else {
                    if (caseArr[arr[0]] == 1 && caseArr[arr[1]] == 1) {
                        caseArr[arr[2]] = 0;
                        isValid = true;
                    }
                    else if (caseArr[arr[0]] == 1 && caseArr[arr[2]] == 1) {
                        caseArr[arr[1]] = 0;
                        isValid = true;
                    }
                    else if (caseArr[arr[1]] == 1 && caseArr[arr[2]] == 1) {
                        caseArr[arr[0]] = 0;
                        isValid = true;
                    }
                }
            }

            if (!isValid) {
                break;
            }
        }

        for (int i = 0; i < caseArr.length; i++) {
            System.out.println(caseArr[i]);
        }
    }
}