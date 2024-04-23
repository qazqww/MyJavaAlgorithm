package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ22233_GahiKeyword {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(in.readLine());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String[] keywords = in.readLine().split(",");
            for (String keyword : keywords) {
                set.remove(keyword);
            }
            sb.append(set.size()).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}