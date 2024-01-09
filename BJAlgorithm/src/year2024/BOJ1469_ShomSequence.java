package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1469_ShomSequence {

    static int N;
    static int[] seq;
    static int[] newSeq;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        seq = new int[N];
        newSeq = new int[2 * N];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(seq);

        makeSeq(0);
        System.out.println(-1);
    }

    static void makeSeq(int index) {
        if (index == 2 * N) {
            StringBuilder sb = new StringBuilder();
            for (int num : newSeq) {
                sb.append(num + " ");
            }
            sb.setLength(sb.length() - 1);
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 0; i < N; i++) {
            newSeq[index] = seq[i];
            if (map.containsKey(seq[i])) { // 이미 나온 수라면
                if (map.get(seq[i]) >= 100) { // 이미 두 번 나왔다면
                    continue;
                }
                int prev = map.get(seq[i]);
                map.put(seq[i], map.get(seq[i]) + 100);
                if (index - prev - 1 == seq[i]) { // 숌 사이 수열의 조건 판별에 맞다면
                    makeSeq(index + 1);
                }
                map.put(seq[i], map.get(seq[i]) - 100);
            }
            else {
                map.put(seq[i], index);
                makeSeq(index + 1);
                map.remove(seq[i]);
            }
            newSeq[index] = 0;
        }
    }
}

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1469_ShomSequence {

    static int N;
    static int[] seq;
    static int[] newSeq;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        seq = new int[N];
        newSeq = new int[2 * N];
        selected = new boolean[N];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(seq);
        Arrays.fill(newSeq, -1);
        makeSeq(0);
        System.out.println(-1);
    }

    static void makeSeq(int index) {
        if (index == 2 * N) {
            StringBuilder sb = new StringBuilder();
            for (int num : newSeq) {
                sb.append(num + " ");
            }
            sb.setLength(sb.length() - 1);
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 0; i < N; i++) {
            int next = index;
            while (next < 2 * N && newSeq[next] != -1) {
                next++;
            }
            if (index != next) {
                makeSeq(next);
            }
            if (selected[i]) {
                continue;
            }
            if (index + seq[i] + 1 < 2 * N && newSeq[index + seq[i] + 1] == -1) {
                newSeq[index] = seq[i];
                newSeq[index + seq[i] + 1] = seq[i];
                selected[i] = true;
                makeSeq(index + 1);
                selected[i] = false;
                newSeq[index + seq[i] + 1] = -1;
                newSeq[index] = -1;
            }
        }
    }
}
 */