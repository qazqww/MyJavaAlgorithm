import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ2023_CuriousPrimeNum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
        for (int i = 1; i < N; i++) {
            ArrayList<Integer> newList = new ArrayList<>();
            for (int num : list) {
                int temp = num * 10;
                loop: for (int j = 1; j < 10; j += 2) {
                    int newNum = temp + j;
                    for (int k = 3; k <= Math.sqrt(newNum); k += 2) {
                        if (newNum % k == 0) {
                            continue loop;
                        }
                    }
                    newList.add(newNum);
                }
            }
            list = new ArrayList<>(newList);
        }
        for (int num : list) {
            System.out.println(num);
        }
    }
}