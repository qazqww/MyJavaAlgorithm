// https://www.acmicpc.net/problem/10816

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class NumCard2_10816 {

	/* 해시테이블? 해시값 같은걸 사용해봄
	 * 20000개의 배열 => 각각 1000 개의 수를 담음
	 * 해시값 = /1000 + 10000
	 * -9999999 ~ -9999000 => arr[0] 에 저장
	 * 0 ~ 999 => arr[10000] 에 저장
	 * List<Map<Int, Int>> 자료구조 사용 (Map의 key : 숫자, value : 나온 횟수)
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());

		Map<Integer, Integer>[] myCardList = new HashMap[20001];
		for (int i = 0; i < 20001; i++) {
			myCardList[i] = new HashMap<>();
		}

		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			int code = num / 1000 + 10000;
			if (myCardList[code].containsKey(num)) {
				myCardList[code].put(num, myCardList[code].get(num) + 1);
			} else {
				myCardList[code].put(num, 1);
			}
		}
		
		int M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			int code = num / 1000 + 10000;
			if (myCardList[code].containsKey(num)) {
				sb.append(myCardList[code].get(num) + " ");
			} else {
				sb.append("0 ");
			}
		}
		
		out.write(sb.toString());
		out.flush();
	}
}