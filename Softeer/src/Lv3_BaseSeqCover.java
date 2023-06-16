import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Lv3_BaseSeqCover {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<String> seqList = new ArrayList<>();

        // 1. 기본 상태에서 합쳐질 수 있는 후보 쌍들을 모두 찾는다.
        // 2. 쌍들을 조합할 수 있는 경우의 수를 모두 시도해본다.
        // 3. 경우의 수마다 최종적으로 조합이 완료되었을 때 초염기서열 개수를 체크한다.

        for (int i = 0; i < N; i++) {
            seqList.add(in.readLine());
        }

        ArrayList<int[]> canUniteList = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (canUnite(seqList.get(i), seqList.get(j))) {
                    canUniteList.add(new int[] { i, j });
                }
            }
        }

        for (int i = 0; i < canUniteList.size(); i++) {

        }
    }

    static boolean canUnite(String str1, String str2) {
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != '.' && str2.charAt(i) != '.'
                    && str1.charAt(i) != str1.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}