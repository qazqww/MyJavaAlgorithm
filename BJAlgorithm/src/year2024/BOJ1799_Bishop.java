package year2024;

/*
 실패 사유)
 메모리 초과로 실패, N >= 8일 때 시간 초과도 발생할 것으로 예상

 해결 방안)
 체스판의 특성(흰 칸, 검은 칸)을 활용.
 검은 칸과 흰 칸의 비숍은 서로 간섭할 일이 없기 때문에 독립적으로 계산할 수 있다.

 수정 결과)
 해결 방안에 따라 시간 복잡도를 O(2^( N * N ))에서 O(2^( N * N / 2 )) 수준으로 줄여
 메모리 초과와 시간 초과를 해결하여 정답
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1799_Bishop {
    static int[] dy = { -1, -1, 1, 1 };
    static int[] dx = { -1, 1, 1, -1 };
    static int N, arrLen;
    static int[] answer = new int[2];
    static int[][] typeArr;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        arrLen = (N * N + 1) / 2;
        map = new boolean[N][N]; // true : 채워짐 (놓을 수 없음)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
            }
        }

        // 흑칸과 백칸의 구분
        typeArr = new int[2][arrLen];
        // 1. N이 홀수인 경우
        if (N % 2 == 1) {
            for (int i = 0; i < N * N; i++) {
                typeArr[i % 2][i / 2] = i;
            }
            typeArr[1][arrLen - 1] = -1;
        }
        // 2. N이 짝수인 경우
        else {
            for (int i = 0; i < N * N; i++) {
                if ((i / N) % 2 == 0) {
                    typeArr[i % 2][i / 2] = i;
                }
                else {
                    typeArr[(i + 1) % 2][i / 2] = i;
                }
            }
        }

        // 비숍을 두는 경우의 수 탐색
        for (int i = 0; i < arrLen; i++) { // 순차적으로 돌며 만나게 되는 첫 번째 빈칸만 탐색
            if (!map[typeArr[0][i] / N][typeArr[0][i] % N]) {
                setBishop(0, i, 0); // 흑칸 탐색
                break;
            }
        }
        for (int i = 0; i < arrLen; i++) {
            if (typeArr[1][i] < 0) {
                break;
            }
            if (!map[typeArr[1][i] / N][typeArr[1][i] % N]) {
                setBishop(1, i, 0); // 흑칸 탐색
                break;
            }
        }
        System.out.println(answer[0] + answer[1]);
    }

    static void setBishop(int type, int index, int cnt) {
        if (index >= arrLen || typeArr[type][index] < 0) {
            return;
        }

        // 말을 놓는 경우
        if (!map[typeArr[type][index] / N][typeArr[type][index] % N]) {
            map[typeArr[type][index] / N][typeArr[type][index] % N] = true;
            answer[type] = Math.max(answer[type], ++cnt);
            ArrayList<int[]> checkedList = checkMap(typeArr[type][index] / N, typeArr[type][index] % N);
            for (int[] place : checkedList) {
                map[place[0]][place[1]] = true;
            }
            setBishop(type, index + 1, cnt);
            for (int[] place : checkedList) {
                map[place[0]][place[1]] = false;
            }
            map[typeArr[type][index] / N][typeArr[type][index] % N] = false;
            cnt--;
        }

        // 말을 놓지 않는 경우
        setBishop(type, index + 1, cnt);
    }

    static ArrayList<int[]> checkMap(int y, int x) {
        ArrayList<int[]> checkedList = new ArrayList<>();
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            while (ny < N && ny >= 0 && nx < N && nx >= 0) {
                if (!map[ny][nx]) {
                    map[ny][nx] = true;
                    checkedList.add(new int[] { ny, nx });
                }
                ny += dy[d];
                nx += dx[d];
            }
        }
        return checkedList;
    }
}