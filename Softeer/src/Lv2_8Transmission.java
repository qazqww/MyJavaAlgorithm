import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Lv2_8Transmission {
    final static int MAX_SPEED = 8;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        Integer[] speed = new Integer[MAX_SPEED];
        for (int i = 0; i < MAX_SPEED; i++) {
            speed[i] = Integer.parseInt(st.nextToken());
        }

        Integer[] asc = new Integer[MAX_SPEED];
        Integer[] desc = new Integer[MAX_SPEED];
        System.arraycopy(speed, 0, asc, 0, MAX_SPEED);
        System.arraycopy(speed, 0, desc, 0, MAX_SPEED);
        Arrays.sort(asc, Comparator.naturalOrder());
        Arrays.sort(desc, Comparator.reverseOrder());

        boolean isAsc = true;
        boolean isDesc = true;
        for (int i = 0; i < MAX_SPEED; i++) {
            if (isAsc && speed[i] != asc[i]) {
                isAsc = false;
            }
            if (isDesc && speed[i] != desc[i]) {
                isDesc = false;
            }
        }

        if (isAsc) {
            System.out.println("ascending");
        }
        else if (isDesc) {
            System.out.println("descending");
        }
        else {
            System.out.println("mixed");
        }
    }
}