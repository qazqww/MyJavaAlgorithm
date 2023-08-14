import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15973_TwoBox {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int xLeft1 = Integer.parseInt(st.nextToken());
        int yUp1 = Integer.parseInt(st.nextToken());
        int xRight1 = Integer.parseInt(st.nextToken());
        int yDown1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int xLeft2 = Integer.parseInt(st.nextToken());
        int yUp2 = Integer.parseInt(st.nextToken());
        int xRight2 = Integer.parseInt(st.nextToken());
        int yDown2 = Integer.parseInt(st.nextToken());

        if (xRight1 < xLeft2 || xRight2 < xLeft1 || yDown1 < yUp2 || yDown2 < yUp1) {
            System.out.println("NULL");
        }
        else if ((xLeft1 == xRight2 && yUp1 == yDown2) || (xLeft2 == xRight1 && yUp2 == yDown1)
                || (xLeft1 == xRight2 && yDown1 == yUp2) || (xLeft2 == xRight1 && yDown2 == yUp1)) {
            System.out.println("POINT");
        }
        else if (xLeft1 == xRight2 || xLeft2 == xRight1 || yDown1 == yUp2 || yDown2 == yUp1) {
            System.out.println("LINE");
        }
        else {
            System.out.println("FACE");
        }
    }
}