package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17837_NewGame2 {
    static final int[] DY = new int[] { 0, 0, 0, -1, 1 };
    static final int[] DX = new int[] { 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        Deque<Integer>[][] horseMap = new ArrayDeque[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                horseMap[i][j] = new ArrayDeque<>();
            }
        }

        int[][] horseInfo = new int[K][]; // { 행, 열, 방향 }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            horseMap[y][x].offerLast(i);
            horseInfo[i] = new int[] { y, x, Integer.parseInt(st.nextToken()) };
        }

        int time = 1;
        boolean isEnd = false;
        while (time <= 1000) {
            for (int i = 0; i < K; i++) {
                // 목적지 탐색
                int y = horseInfo[i][0];
                int x = horseInfo[i][1];
                int ny = y + DY[horseInfo[i][2]];
                int nx = x + DX[horseInfo[i][2]];

                // 파랑 : 말 방향 바꾸고 이동
                if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] == 2) {
                    changeDir(horseInfo[i]);
                    ny = y + DY[horseInfo[i][2]];
                    nx = x + DX[horseInfo[i][2]];
                }
                // 바꾼 후 파랑 : 가만히 있기
                if (ny >= N || ny < 0 || nx >= N || nx < 0 || map[ny][nx] == 2) {
                    continue;
                }
                // 빨강 : 말 순서 바꿔 이동
                else if (map[ny][nx] == 1) {
                    while (horseMap[y][x].peekLast() != i) {
                        horseInfo[horseMap[y][x].peekLast()][0] = ny;
                        horseInfo[horseMap[y][x].peekLast()][1] = nx;
                        horseMap[ny][nx].offerLast(horseMap[y][x].pollLast());
                    }
                    horseInfo[i][0] = ny;
                    horseInfo[i][1] = nx;
                    horseMap[ny][nx].offerLast(horseMap[y][x].pollLast());
                }
                // 하양 : 말 이동
                else {
                    Stack<Integer> tempStack = new Stack<>();
                    while (horseMap[y][x].peekLast() != i) {
                        horseInfo[horseMap[y][x].peekLast()][0] = ny;
                        horseInfo[horseMap[y][x].peekLast()][1] = nx;
                        tempStack.push(horseMap[y][x].pollLast());
                    }
                    horseInfo[i][0] = ny;
                    horseInfo[i][1] = nx;
                    tempStack.push(horseMap[y][x].pollLast());

                    while (!tempStack.isEmpty()) {
                        horseMap[ny][nx].offerLast(tempStack.pop());
                    }
                }

                if (horseMap[ny][nx].size() >= 4) {
                    isEnd = true;
                    break;
                }
            }
            if (isEnd) {
                break;
            }
            time++;
        }
        System.out.println(time > 1000 ? -1 : time);
    }

    static void changeDir(int[] horseInfo) {
        switch (horseInfo[2]) {
            case 1:
                horseInfo[2] = 2;
                break;
            case 2:
                horseInfo[2] = 1;
                break;
            case 3:
                horseInfo[2] = 4;
                break;
            case 4:
                horseInfo[2] = 3;
                break;
        }
    }
}
/*
1. 말이 4개 이상 쌓이는 순간 종료
2. 말 쌓기 처리 -> deque, 대상이 빨강이면 뒤에서 빼서 앞으로 집어넣기
3. 말 배열 -> 위치, 방향
4. 말 저장 -> 번호
 */