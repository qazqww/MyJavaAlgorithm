import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1900_Wrestler {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[][] players = new int[N][];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            players[i] = new int[] { i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0 };
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int iPower = players[i][1] + players[j][1] * players[i][2];
                int jPower = players[j][1] + players[i][1] * players[j][2];

                if (iPower > jPower) {
                    players[i][3]++;
                }
                else {
                    players[j][3]++;
                }
            }
        }

        Arrays.sort(players, (a, b) -> b[3] - a[3]);
        for (int i = 0; i < N; i++) {
            System.out.println(players[i][0]);
        }
    }
}