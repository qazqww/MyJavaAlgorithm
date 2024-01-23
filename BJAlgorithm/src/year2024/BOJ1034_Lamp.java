package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1034_Lamp {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String str = in.readLine();
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            }
            else {
                map.put(str, 1);
            }
        }

        int K = Integer.parseInt(in.readLine());

        int max = 0;
        for (String str : map.keySet()) {
            int cntZero = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') {
                    cntZero++;
                }
            }

            if (cntZero <= K && cntZero % 2 == K % 2) {
                max = Math.max(max, map.get(str));
            }
        }

        System.out.println(max);
    }
}
/*
1. Map에 넣음
2. 각 key의 개수, 최소 몇 번의 스위치가 필요한지, 홀수인지 짝수인지

 */