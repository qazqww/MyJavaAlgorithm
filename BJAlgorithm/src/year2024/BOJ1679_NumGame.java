package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1679_NumGame {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(in.readLine());

        Set<Integer> result = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            result.add(nums[i]);
            list.add(nums[i]);
        }

        for (int i = 1; i < K; i++) {
            ArrayList<Integer> newList = new ArrayList<>();
            for (int num : list) {
                for (int j = 0; j < N; j++) {
                    if (!result.contains(num + nums[j])) {
                        result.add(num + nums[j]);
                        newList.add(num + nums[j]);
                    }
                }
            }
            list = new ArrayList<>(newList);
        }

        int check = 1;
        while (result.contains(check)) {
            check++;
        }
        if (check % 2 == 0) {
            System.out.println("holsoon win at " + check);
        }
        else {
            System.out.println("jjaksoon win at " + check);
        }
    }
}