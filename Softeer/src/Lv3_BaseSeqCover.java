import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lv3_BaseSeqCover {
    static ArrayList<int[]> canUniteList = new ArrayList<>();
    static boolean[] tried;
    static int N, M, min;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        ArrayList<String> seqList = new ArrayList<>();

        // 1. 기본 상태에서 합쳐질 수 있는 후보 쌍들을 모두 찾는다.
        // 2. 쌍들을 조합할 수 있는 경우의 수를 모두 시도해본다.
        // 3. 경우의 수마다 최종적으로 조합이 완료되었을 때 초염기서열 개수를 체크한다.

        for (int i = 0; i < N; i++) {
            seqList.add(in.readLine());
        }

        canUniteList = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (canUnite(seqList.get(i), seqList.get(j))) {
                    canUniteList.add(new int[] { i, j });
                }
            }
        }

        tried = new boolean[canUniteList.size()];
        unite(N, seqList);
        System.out.println(min);
    }

    static void unite(int cnt, ArrayList<String> list) {
        min = Math.min(min, cnt);
        for (int i = 0; i < canUniteList.size(); i++) {
            if (tried[i]) {
                continue;
            }

            ArrayList<String> newList = new ArrayList<>();
            for (String str : list) {
                newList.add(str);
            }

            tried[i] = true;
            int[] target = canUniteList.get(i);
            if (canUnite(newList.get(target[0]), newList.get(target[1]))) {
                newList.set(target[0], unite(newList.get(target[0]), newList.get(target[1])));
                newList.set(target[1], "");
                unite(cnt - 1, newList);
            }
            tried[i] = false;
        }
    }

    static String unite(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(i) || (str1.charAt(i) != '.' && str2.charAt(i) == '.')) {
                sb.append(str1.charAt(i));
            }
            else if (str1.charAt(i) == '.' && str2.charAt(i) != '.') {
                sb.append(str2.charAt(i));
            }
        }
        return sb.toString();
    }

    static boolean canUnite(String str1, String str2) {
        if (str1.equals("") || str2.equals("")) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != '.' && str2.charAt(i) != '.' && str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}