package year2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1082_RoomNum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] price = new int[N];
        StringBuilder answer = new StringBuilder();

        int minPrice = 100;
        int minNum = -1;
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
            if (i > 0 && price[i] <= minPrice) {
                minPrice = price[i];
                minNum = i;
            }
        }
        int M = Integer.parseInt(in.readLine());

        boolean zeroMin = price[0] < minPrice;
        int money = M;

        if (money >= minPrice) {
            answer.append(minNum);
            money -= minPrice;
        }
        else if (zeroMin) {
            System.out.println(0);
            return;
        }

        if (zeroMin) {
            while (money >= price[0]) {
                answer.append('0');
                money -= price[0];
            }
        }
        else {
            while (money >= minPrice) {
                answer.append(minNum);
                money -= minPrice;
            }
        }

        for (int i = 0; i < answer.length(); i++) {
            int curPrice = price[answer.charAt(i) - '0'];
            if (money < price[0] - curPrice && money < minPrice - curPrice) {
                break;
            }

            for (int j = N - 1; j >= 0; j--) {
                if (price[j] - curPrice <= money) {
                    money -= price[j] - curPrice;
                    answer.replace(i, i + 1, String.valueOf(j));
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
/*
1. 최대 몇 자리까지 만들 수 있는가?
ㄴ 첫 자리는 0 제외, 가장 싼 값으로 채워보기
7 6 6 원으로 100 만들기 가능

2. 남은 돈으로 첫 숫자부터 업그레이드 해보기
 */