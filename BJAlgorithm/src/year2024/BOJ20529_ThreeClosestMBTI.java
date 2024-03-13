package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ20529_ThreeClosestMBTI {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(in.readLine());
            st = new StringTokenizer(in.readLine());
            if (N >= 33) {
                System.out.println(0);
            }
            else if (N >= 17) {
                Map<String, Integer> count = new HashMap<>();
                boolean isSame = false;
                for (int i = 0; i < N; i++) {
                    String mbti = st.nextToken();
                    if (count.containsKey(mbti)) {
                        count.put(mbti, count.get(mbti) + 1);
                        if (count.get(mbti) >= 3) {
                            isSame = true;
                            break;
                        }
                    }
                    else {
                        count.put(mbti, 1);
                    }
                }
                System.out.println(isSame ? 0 : 2);
            }
            else {
                int answer = Integer.MAX_VALUE;
                int[] mbtis = new int[N];
                for (int i = 0; i < N; i++) {
                    mbtis[i] = getMBTI(st.nextToken());
                }
                for (int i = 0; i < N - 2; i++) {
                    for (int j = i + 1; j < N - 1; j++) {
                        int tempResult = getGap(mbtis[i], mbtis[j]);
                        for (int k = j + 1; k < N; k++) {
                            int result = tempResult
                                    + getGap(mbtis[i], mbtis[k])
                                    + getGap(mbtis[j], mbtis[k]);
                            answer = Math.min(answer, result);
                        }
                    }
                }
                System.out.println(answer);
            }
        }
    }

    static int getMBTI(String str) {
        int mbti = 0;
        for (char ch : str.toCharArray()) {
            switch (ch) {
                case 'I':
                    mbti += 1 << 3;
                    break;
                case 'N':
                    mbti += 1 << 2;
                    break;
                case 'T':
                    mbti += 1 << 1;
                    break;
                case 'P':
                    mbti += 1 << 0;
                    break;
            }
        }
        return mbti;
    }

    static int getGap(int num1, int num2) {
        int result = 0;
        int gap = num1 ^ num2;
        for (int i = 0; i < 4; i++) {
            if ((gap & (1 << i)) >= 1) {
                result++;
            }
        }
        return result;
    }
}