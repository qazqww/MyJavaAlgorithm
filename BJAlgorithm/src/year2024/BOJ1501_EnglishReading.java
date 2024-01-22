package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1501_EnglishReading {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] hashes = new int[N];

        // 사전에 들어올 단어 입력받기
        for (int i = 0; i < N; i++) {
            String str = in.readLine();

            // 첫 글자와 끝 글자를 제외한 알파벳을 담을 char array
            char[] text = new char[0];
            if (str.length() > 2) {
                text = str.substring(1, str.length() - 1).toCharArray();
            }

            // 이 배열을 정렬하여 같은 구성이라면 같은 결과가 나오도록 하였다
            Arrays.sort(text);
            hashes[i] = getHash(str.charAt(0), str.charAt(str.length() - 1), text, str.length());
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(in.readLine());

        // 해석할 문자 입력받기
        loop: for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int answer = 1;

            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                char[] text = new char[0];
                if (str.length() > 2) {
                    text = str.substring(1, str.length() - 1).toCharArray();
                }
                Arrays.sort(text);
                int hash = getHash(str.charAt(0), str.charAt(str.length() - 1), text, str.length());

                // 사전의 각 단어에 대해 일치 여부를 판단
                int correct = 0;
                for (int j = 0; j < N; j++) {
                    if (hash == hashes[j]) {
                        correct++;
                    }
                }

                if (correct == 0) {
                    sb.append(0 + "\n");
                    continue loop;
                }
                answer *= correct;
            }
            sb.append(answer + "\n");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        System.out.println(sb);
    }

    // 단어 정보에 대한 해시를 구하는 메소드
    static int getHash(char start, char end, char[] text, int length) {
        StringBuilder sb = new StringBuilder();
        sb.append(start).append(end).append(length);
        for (char ch : text) {
            sb.append(ch);
        }
        return sb.toString().hashCode();
    }
}