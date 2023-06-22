import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Lv2_Billboard {
    static boolean[][] numInfo = new boolean[11][7]; // [표시할 숫자][전구 스위치]

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());
        setup();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(in.readLine());
            String from = st.nextToken();
            String to = st.nextToken();

            String none = "";
            for (int i = 0; i < Math.abs(from.length() - to.length()); i++) {
                none += "X";
            }

            if (from.length() < to.length()) {
                from = none + from;
            }
            else {
                to = none + to;
            }

            int answer = 0;
            for (int i = 0; i < from.length(); i++) {
                int num1 = from.charAt(i) == 'X' ? 10 : from.charAt(i) - '0';
                int num2 = to.charAt(i) == 'X' ? 10 : to.charAt(i) - '0';
                answer += getCnt(num1, num2);
            }
            System.out.println(answer);
        }
    }

    static void setup() {
        numInfo[0][0] = numInfo[0][1] = numInfo[0][2] = numInfo[0][4] = numInfo[0][5] = numInfo[0][6] = true;
        numInfo[1][2] = numInfo[1][5] = true;
        numInfo[2][0] = numInfo[2][2] = numInfo[2][3] = numInfo[2][4] = numInfo[2][6] = true;
        numInfo[3][0] = numInfo[3][2] = numInfo[3][3] = numInfo[3][5] = numInfo[3][6] = true;
        numInfo[4][1] = numInfo[4][2] = numInfo[4][3] = numInfo[4][5] = true;
        numInfo[5][0] = numInfo[5][1] = numInfo[5][3] = numInfo[5][5] = numInfo[5][6] = true;
        numInfo[6][0] = numInfo[6][1] = numInfo[6][3] = numInfo[6][4] = numInfo[6][5] = numInfo[6][6] = true;
        numInfo[7][0] = numInfo[7][1] = numInfo[7][2] = numInfo[7][5] = true;
        numInfo[8][0] = numInfo[8][1] = numInfo[8][2] = numInfo[8][3] = numInfo[8][4] = numInfo[8][5] = numInfo[8][6] = true;
        numInfo[9][0] = numInfo[9][1] = numInfo[9][2] = numInfo[9][3] = numInfo[9][5] = numInfo[9][6] = true;
    }

    static int getCnt(int num1, int num2) {
        int cnt = 0;
        for (int i = 0; i < 7; i++) {
            if (numInfo[num1][i] != numInfo[num2][i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
/*
    0
1       2
    3
4       5
    6
 */