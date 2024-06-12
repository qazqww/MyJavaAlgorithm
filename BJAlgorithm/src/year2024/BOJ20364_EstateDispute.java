package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ20364_EstateDispute {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = in.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int Q = Integer.parseInt(temp[1]);
        Set<Integer> owned = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int estate = Integer.parseInt(in.readLine());
            int explore = estate;
            int meetOwned = -1;
            while (explore > 0) {
                if (owned.contains(explore)) {
                    meetOwned = explore;
                }
                explore >>= 1;
            }
            if (meetOwned < 0) {
                sb.append("0\n");
                owned.add(estate);
            }
            else {
                sb.append(meetOwned).append("\n");
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}