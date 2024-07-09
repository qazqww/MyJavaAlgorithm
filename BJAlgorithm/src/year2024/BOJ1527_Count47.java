package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1527_Count47 {
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        setup();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int A = Integer.parseInt(temp[0]);
        int B = Integer.parseInt(temp[1]);

        int answer = 0;
        for (String str : list) {
            int num = Integer.parseInt(str);
            if (num >= A && num <= B) {
                answer++;
            }
            if (num > B) {
                break;
            }
        }
        System.out.println(answer);
    }

    static void setup() {
        list.add("4");
        list.add("7");

        int cnt = 0;
        int start = 0;
        int end = 0;
        while (cnt < 8) {
            end = list.size();
            for (int i = start; i < end; i++) {
                list.add("4" + list.get(i));
            }
            for (int i = start; i < end; i++) {
                list.add("7" + list.get(i));
            }
            start = end;
            cnt++;
        }
    }
}