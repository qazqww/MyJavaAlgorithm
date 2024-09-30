package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ20366_DoYouWannaBuildASnowman {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] snowball = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            snowball[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(snowball);

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int oldHeight = snowball[i] + snowball[j];

                int front = 0;
                int rear = N - 1;

                while (front < rear) {
                    if (front == i || front == j) {
                        front++;
                        continue;
                    }
                    if (rear == i || rear == j) {
                        rear--;
                        continue;
                    }

                    int newHeight = snowball[front] + snowball[rear];
                    answer = Math.min(answer, Math.abs(oldHeight - newHeight));
                    if (newHeight > oldHeight) {
                        rear--;
                    }
                    else {
                        front++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
/*
4
1 10 100 1000
 */