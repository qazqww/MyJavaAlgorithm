package year2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ1744_NumBind {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        int zeroCnt = 0, total = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(in.readLine());
            if (num == 0) {
                zeroCnt++;
            }
            else if (num > 0) {
                plus.add(num);
            }
            else {
                minus.add(num);
            }
        }

        Collections.sort(plus);
        Collections.reverse(plus);
        Collections.sort(minus);

        for (int i = 0; i < minus.size(); i++) {
            if (i < minus.size() - 1) {
                total += Math.max(minus.get(i) * minus.get(i + 1), minus.get(i) + minus.get(i + 1));
                i++;
            }
            else if (zeroCnt == 0) {
                total += minus.get(i);
            }
        }

        for (int i = 0; i < plus.size(); i++) {
            if (i < plus.size() - 1) {
                total += Math.max(plus.get(i) * plus.get(i + 1), plus.get(i) + plus.get(i + 1));
                i++;
            }
            else {
                total += plus.get(i);
            }
        }

        System.out.println(total);
    }
}

/*
2
-8
0


 */