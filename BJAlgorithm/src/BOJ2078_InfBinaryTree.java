import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2078_InfBinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int left = 0;
        int right = 0;

        while (A > 1 && B > 1) {
            if (A > B) {
                A -= B;
                left++;
            }
            else {
                B -= A;
                right++;
            }
        }

        if (A > 1) {
            left += A - 1;
        }
        else if (B > 1) {
            right += B - 1;
        }

        System.out.println(left + " " + right);
    }
}