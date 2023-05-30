// https://www.acmicpc.net/problem/1699

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1699_SumOfSquares {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        ArrayList<Integer> first = new ArrayList<>();
        int cnt = 1;

        for (int i = 1; i <= Math.sqrt(N); i++) {
            first.add((int)Math.pow(i, 2));
        }

        if (first.contains(N)) {
            System.out.println(cnt);
            return;
        }

        ArrayList<Integer> next = new ArrayList<>(first);
        Set<Integer> temp = new HashSet<>();
        while(true) {
            cnt++;
            for (int n : next) {
                for (int f : first) {
                    if (n + f > N) {
                        break;
                    }
                    else if (n + f == N) {
                        System.out.println(cnt);
                        return;
                    }
                    temp.add(n + f);
                }
            }
            next = new ArrayList<>(temp);
            Collections.sort(next);
        }
    }
}