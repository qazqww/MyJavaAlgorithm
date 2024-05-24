package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ13022_Wwoollff {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        if (str.charAt(0) != 'w') {
            System.out.println(0);
            return;
        }

        int[] wolf = new int[4];
        wolf[0]++;

        for (int i = 1; i < str.length(); i++) {
            if ((str.charAt(i) == 'w' && str.charAt(i - 1) != 'f' && str.charAt(i - 1) != 'w')
                    || (str.charAt(i) == 'o' && str.charAt(i - 1) != 'w' && str.charAt(i - 1) != 'o')
                    || (str.charAt(i) == 'l' && str.charAt(i - 1) != 'o' && str.charAt(i - 1) != 'l')
                    || (str.charAt(i) == 'f' && str.charAt(i - 1) != 'l' && str.charAt(i - 1) != 'f')) {
                System.out.println(0);
                return;
            }

            if (str.charAt(i) == 'w' && str.charAt(i - 1) == 'f') {
                if (wolf[0] != wolf[1] || wolf[1] != wolf[2] || wolf[2] != wolf[3]) {
                    System.out.println(0);
                    return;
                }
                wolf[0] = wolf[1] = wolf[2] = wolf[3] = 0;
            }

            switch (str.charAt(i)) {
                case 'w':
                    wolf[0]++;
                    break;
                case 'o':
                    wolf[1]++;
                    break;
                case 'l':
                    wolf[2]++;
                    break;
                case 'f':
                    wolf[3]++;
                    break;
            }
        }

        if (wolf[0] != wolf[1] || wolf[1] != wolf[2] || wolf[2] != wolf[3]) {
            System.out.println(0);
            return;
        }
        System.out.println(1);
    }
}