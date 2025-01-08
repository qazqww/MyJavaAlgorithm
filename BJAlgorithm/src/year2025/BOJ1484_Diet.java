package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ1484_Diet {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(in.readLine());

        ArrayList<Integer> answer = new ArrayList<>();
        int idx = 2;
        int base = 3;
        int gap = 5;
        int multi = 2;
        while (base <= G) {
            if ((G - base) % multi == 0) {
                answer.add(idx + (G - base) / multi);
            }
            idx += 1;
            base += gap;
            gap += 2;
            multi += 2;
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num + "\n");
        }
        if (sb.length() == 0) {
            System.out.println(-1);
        }
        else {
            sb.setLength(sb.length() - 1);
            System.out.println(sb);
        }
    }
}
/*
1 4 9 16 25 36 49 64 81 100
 3 5 7 9 11 13 15 17 19 => 3 + 2n
 8 12 16 20 24 ... => 8 + 4n
 15 21 27 33 ... => 15 + 6n
 24 + 8n
 35 + 10n

G - 3 = 2n (n >= 0인 자연수)
G - 8 = 4n
G - 15 = 6n
 */