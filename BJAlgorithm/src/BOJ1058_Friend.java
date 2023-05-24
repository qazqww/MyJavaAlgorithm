// https://www.acmicpc.net/problem/1058

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BOJ1058_Friend {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        ArrayList<Integer>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'Y') {
                    list[i].add(j);
                }
            }
        }

        int max = 0;
        Set<Integer> set;
        for (int i = 0; i < N; i++) {
            set = new HashSet<>(list[i]);
            for (int num : list[i]) {
                set.addAll(list[num]);
            }
            max = Math.max(max, set.size() - 1);
        }
        System.out.println(max);
    }
}