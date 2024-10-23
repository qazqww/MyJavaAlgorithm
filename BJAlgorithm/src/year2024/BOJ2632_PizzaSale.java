package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ2632_PizzaSale {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(in.readLine());
        String[] temp = in.readLine().split(" ");
        int cntA = Integer.parseInt(temp[0]);
        int cntB = Integer.parseInt(temp[1]);
        int totalA = 0;
        int totalB = 0;

        int[] pizzaA = new int[cntA];
        int[] pizzaB = new int[cntB];
        for (int i = 0; i < cntA; i++) {
            pizzaA[i] = Integer.parseInt(in.readLine());
            totalA += pizzaA[i];
        }
        for (int i = 0; i < cntB; i++) {
            pizzaB[i] = Integer.parseInt(in.readLine());
            totalB += pizzaB[i];
        }

        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();
        mapA.put(0, 0);
        mapB.put(0, 0);

        // A와 B 각 피자에서 나올 수 있는 크기와 개수를 체크
        for (int i = 0; i < cntA; i++) {
            int sum = pizzaA[i];
            mapA.putIfAbsent(sum, 0);
            mapA.put(sum, mapA.get(sum) + 1);
            for (int j = i + 1; j != i; j++) {
                if (j >= cntA) {
                    j = 0;
                }
                sum += pizzaA[j];
                if (i == j || sum > target) {
                    break;
                }
                mapA.putIfAbsent(sum, 0);
                mapA.put(sum, mapA.get(sum) + 1);
            }
        }
        for (int i = 0; i < cntB; i++) {
            int sum = pizzaB[i];
            mapB.putIfAbsent(sum, 0);
            mapB.put(sum, mapB.get(sum) + 1);
            for (int j = i + 1; j != i; j++) {
                if (j >= cntB) {
                    j = 0;
                }
                sum += pizzaB[j];
                if (i == j || sum > target) {
                    break;
                }
                mapB.putIfAbsent(sum, 0);
                mapB.put(sum, mapB.get(sum) + 1);
            }
        }
        // 피자 전체를 사용하는 경우의 중복 카운팅을 피하기 위해 따로 값을 설정
        mapA.put(totalA, 1);
        mapB.put(totalB, 1);

        // A 부분의 크기 + B 부분의 크기가 목표 크기가 되는 경우를 카운트
        // A == 0 or B == 0 이면 value의 합으로, 그 외엔 곱으로 경우의 수를 뽑아냄
        int answer = 0;
        for (int key : mapA.keySet()) {
            if (mapB.containsKey(target - key)) {
                answer += (key == 0 || key == target) ?
                        mapA.get(key) + mapB.get(target - key) : mapA.get(key) * mapB.get(target - key);
            }
        }
        System.out.println(answer);
    }
}