package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ2342_DDR {
    final static int INF = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        Map<Integer, Integer> stateIdx = new HashMap<>();
        int[] state = { 0b11000, 0b10100, 0b10010, 0b10001, 0b01100, 0b01010, 0b01001, 0b00110, 0b00101, 0b00011 };
        for (int i = 0; i < state.length; i++) {
            stateIdx.put(state[i], i);
        }

        int[] power = new int[10];
        Arrays.fill(power, INF);

        int next = Integer.parseInt(st.nextToken());
        // 첫번째 무브
        for (int i = 0; i < 4; i++) {
            if ((state[i] & (1 << (next - 1))) >= 1) {
                power[i] = 2;
            }
        }

        while (st.hasMoreTokens()) {
            next = Integer.parseInt(st.nextToken());
            if (next == 0) {
                break;
            }

            int[] nextPower = new int[10];
            Arrays.fill(nextPower, INF);
            for (int i = 0; i < state.length; i++) {
                if (power[i] >= INF) {
                    continue;
                }
                // 이미 다음 위치를 밟고 있는 경우
                int nextPos = 1 << (next - 1);
                if ((state[i] & nextPos) >= 1) {
                    nextPower[i] = Math.min(nextPower[i], power[i] + 1);
                }
                else {
                    for (int j = 1; j <= 5; j++) {
                        int curPos = 1 << (j - 1);
                        if (curPos == nextPos || (state[i] & curPos) == 0) {
                            continue;
                        }

                        int newStateIdx = stateIdx.get(state[i] - curPos + nextPos);
                        int addedPower = 3; // 옆칸으로 발을 옮겨 다음 위치를 밟는 경우를 default로

                        // 시작점의 발을 옮겨 다음 위치를 밟는 경우
                        if (j == 5) {
                            addedPower = 2;
                        }
                        // 반대쪽의 발을 옮겨 다음 위치를 밟는 경우
                        else if ((curPos >> 2) == nextPos || (curPos << 2) == nextPos) {
                            addedPower = 4;
                        }
                        nextPower[newStateIdx] = Math.min(nextPower[newStateIdx], power[i] + addedPower);
                    }
                }
            }

            for (int i = 0; i < power.length; i++) {
                power[i] = nextPower[i];
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < power.length; i++) {
            answer = Math.min(answer, power[i]);
        }
        System.out.println(answer >= 1_000_000 ? answer - 1_000_000 : answer);
    }
}
/*
        // 1. 4가 주어진다면, 4가 포함된 상태들을 업데이트
        // 1-1. 4가 포함된 수들은 +1를 한다.
        // 2. 4가 포함되지 않은 수들에선 2가지 경우가 나온다. 이들을 계산해야 한다.
        // 3. 각 이동에 드는 힘을 더한다.
        // 4. 각 상태에서 최소값을 취해서 갱신한다.

04321

2 : & 1 << 4 >= 1
4 : == << 2 or == >> 2
1 : same
3 : others

 */