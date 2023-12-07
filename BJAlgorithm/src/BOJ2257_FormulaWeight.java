import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2257_FormulaWeight {
    static String formula;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        formula = in.readLine();
        System.out.println(weight(0)[0]);
    }

    // return : { sum, index }
    static int[] weight(int index) {
        int sum = 0;
        while (index < formula.length()) {
            if (formula.charAt(index) == '(') {
                int[] result = weight(index + 1);
                sum += result[0];
                index = result[1];
            }
            else {
                int multiple = 1;
                if (index + 1 < formula.length() &&
                        formula.charAt(index + 1) > '1' && formula.charAt(index + 1) <= '9') {
                    multiple = formula.charAt(index + 1) - '0';
                }

                if (multiple == 1) {
                    switch (formula.charAt(index)) {
                        case ')':
                            return new int[] { sum, index };
                        case 'C':
                            sum += 12;
                            break;
                        case 'H':
                            sum += 1;
                            break;
                        case 'O':
                            sum += 16;
                            break;
                    }
                }
                else {
                    switch (formula.charAt(index)) {
                        case ')':
                            return new int[] { sum * multiple, index + 1 };
                        case 'C':
                            sum += 12 * multiple;
                            break;
                        case 'H':
                            sum += 1 * multiple;
                            break;
                        case 'O':
                            sum += 16 * multiple;
                            break;
                    }
                    index++;
                }
            }
            index++;
        }
        return new int[] { sum, index + 1 };
    }
}