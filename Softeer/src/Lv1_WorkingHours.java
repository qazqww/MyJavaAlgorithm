import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Lv1_WorkingHours {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int total = 0;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(in.readLine());
            String[] open = st.nextToken().split(":");
            String[] close = st.nextToken().split(":");

            total += (Integer.parseInt(close[0]) - Integer.parseInt(open[0])) * 60;
            total += Integer.parseInt(close[1]) - Integer.parseInt(open[1]);
        }

        System.out.println(total);
    }
}