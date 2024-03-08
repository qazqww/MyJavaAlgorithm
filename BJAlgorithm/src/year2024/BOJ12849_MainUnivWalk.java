package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ12849_MainUnivWalk {
    static final int BUILDING = 8;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer>[] list = new ArrayList[BUILDING];
        for (int i = 0; i < BUILDING; i++) {
            list[i] = new ArrayList<>();
        }
        addRoute(list);
        long[][] routeCnt = new long[2][BUILDING];
        routeCnt[0][0] = 1;
        int D = Integer.parseInt(in.readLine());
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < BUILDING; j++) {
                long sum = 0;
                for (int nearby : list[j]) {
                    sum += routeCnt[0][nearby];
                    sum %= 1_000_000_007;
                }
                routeCnt[1][j] = sum;
            }
            for (int j = 0; j < BUILDING; j++) {
                routeCnt[0][j] = routeCnt[1][j];
            }
        }
        System.out.println(routeCnt[1][0]);
    }

    static void addRoute(List<Integer>[] list) {
        list[0].add(1);
        list[0].add(2);
        list[1].add(0);
        list[1].add(2);
        list[1].add(3);
        list[2].add(0);
        list[2].add(1);
        list[2].add(3);
        list[2].add(5);
        list[3].add(1);
        list[3].add(2);
        list[3].add(4);
        list[3].add(5);
        list[4].add(3);
        list[4].add(5);
        list[4].add(6);
        list[5].add(2);
        list[5].add(3);
        list[5].add(4);
        list[5].add(7);
        list[6].add(4);
        list[6].add(7);
        list[7].add(5);
        list[7].add(6);
    }
}