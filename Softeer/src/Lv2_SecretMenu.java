import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Lv2_SecretMenu {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int answerCnt = Integer.parseInt(st.nextToken());
        int inputCnt = Integer.parseInt(st.nextToken());
        int maxNum = Integer.parseInt(st.nextToken());

        String answer = in.readLine().replaceAll(" ", "");
        String input = in.readLine().replaceAll(" ", "");

        for (int i = 0; i < inputCnt - answerCnt + 1; i++) {
            if (input.charAt(i) == answer.charAt(0)) {
                if (input.substring(i, i + answerCnt).equals(answer)) {
                    System.out.println("secret");
                    return;
                }
            }
        }
        System.out.println("normal");
    }
}
