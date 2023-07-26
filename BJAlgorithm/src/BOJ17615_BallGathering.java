import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17615_BallGathering {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        String str = in.readLine();

        char first = str.charAt(0);
        char last = str.charAt(str.length() - 1);
        int fromFirst = 0;
        int fromLast = 0;
        int other = 0;

        boolean checking = true;
        for (int i = 1; i < str.length(); i++) {
            if (checking && first != str.charAt(i)) {
                checking = false;
            }
            if (!checking && first != str.charAt(i)) {
                other++;
            }
            if (!checking && first == str.charAt(i)) {
                fromFirst++;
            }
        }
        checking = true;
        for (int i = str.length() - 2; i >= 0; i--) {
            if (checking && last != str.charAt(i)) {
                checking = false;
            }
            if (!checking && last == str.charAt(i)) {
                fromLast++;
            }
        }

        System.out.println(Math.min(Math.min(fromFirst, fromLast), other));
    }
}