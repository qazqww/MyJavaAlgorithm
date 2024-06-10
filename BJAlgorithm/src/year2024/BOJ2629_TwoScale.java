package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2629_TwoScale {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] weight = new int[N];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(in.readLine());
        int[] bead = new int[M];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < M; i++) {
            bead[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<int[]> list = new ArrayList<>(); // 구할 수 있는 수, 사용된 추(비트값)
        Set<Integer> gotNumber = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int size = list.size();
            for (int j = 0; j < size; j++) {
                int num = list.get(j)[0] + weight[i];
                if (!gotNumber.contains(num)) {
                    list.add(new int[] { num, list.get(j)[1] + (1 << i) });
                    gotNumber.add(num);
                }
            }
            if (!gotNumber.contains(weight[i])) {
                list.add(new int[]{weight[i], 1 << i});
                gotNumber.add(weight[i]);
            }
        }

        // 정렬을 활용하여 시간을 더 줄일 수 있을지도
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }

                int bitA = list.get(i)[1];
                int bitB = list.get(j)[1];

                int sum = bitA + bitB;
                int bitSum = bitA | bitB;

                int num = list.get(i)[0] - list.get(j)[0];

                // 상호 배타적인 집합이고, 차이가 양수일 때
                if (sum == bitSum && num > 0) {
                    gotNumber.add(num);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : bead) {
            if (gotNumber.contains(num)) {
                sb.append("Y ");
            }
            else {
                sb.append("N ");
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}