import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1105_Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str = in.readLine().split(" ");
        String L = str[0];
        String R = str[1];

        int temp = Integer.parseInt(L);
        int Lcnt = 0;
        int Rcnt = 0;

        while (temp > 0) {
            Lcnt++;
            temp /= 10;
        }
        temp = Integer.parseInt(R);
        while (temp > 0) {
            Rcnt++;
            temp /= 10;
        }

        String newL = "";
        for (int i = 0; i < Rcnt - Lcnt; i++) {
            newL += "0";
        }
        newL += L;

        int answer = 0;
        for (int i = 0; i < R.length(); i++) {
            if (newL.charAt(i) != R.charAt(i)) {
                break;
            }
            if (newL.charAt(i) == '8') {
                answer++;
            }
        }
        System.out.println(answer);
    }
}