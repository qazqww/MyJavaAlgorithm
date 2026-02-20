package year2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1089_StartlinkTower {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] numInfo = new int[] { Integer.parseInt("111101101101111", 2), Integer.parseInt("001001001001001", 2), Integer.parseInt("111001111100111", 2), Integer.parseInt("111001111001111", 2), Integer.parseInt("101101111001001", 2), Integer.parseInt("111100111001111", 2), Integer.parseInt("111100111101111", 2), Integer.parseInt("111001001001001", 2), Integer.parseInt("111101111101111", 2), Integer.parseInt("111101111001111", 2), };

        char[][] board = new char[5][];
        for (int i = 0; i < 5; i++) {
            board[i] = in.readLine().toCharArray();
        }

        ArrayList<Integer>[] possibleList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            possibleList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int num = 0;
            for (int y = 0; y < 5; y++) {
                for (int x = i * 4; x < i * 4 + 3; x++) {
                    if (board[y][x] == '#') {
                        num |= 1;
                    }
                    else {
                        num |= 0;
                    }
                    num <<= 1;
                }
            }
            num >>= 1;

            for (int j = 0; j < 10; j++) {
                if ((num & numInfo[j]) == num) {
                    possibleList[i].add(j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (possibleList[i].isEmpty()) {
                System.out.println(-1);
                return;
            }
        }

        double result = 0;
        int a = N;
        for (int i = 0; i < possibleList.length; i++) {
            double d = 0;
            double pow = Math.pow(10, --a);
            for (int j = 0; j < possibleList[i].size(); j++) {
                d += possibleList[i].get(j);
            }
            d /= possibleList[i].size();
            result += d * pow;
        }
        System.out.println(result);
    }
}