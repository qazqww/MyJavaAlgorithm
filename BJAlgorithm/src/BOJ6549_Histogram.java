// https://www.acmicpc.net/problem/6549

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ6549_Histogram {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(in.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			if (N == 0)
				return;
			
			long answer = -1;
			
			Map<Integer, Integer> map = new HashMap<>();
			int preNum = Integer.parseInt(st.nextToken());
			map.put(preNum, 1);
			
			for (int i = 1; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				if (num < preNum) { // 이전보다 작으면
					List<Integer> remove = new ArrayList<>();
					int longest = -1;
					
					for (int key : map.keySet()) {
						int val = map.get(key);
						
						if (key > num) { // 큰거 걸러내기
							answer = Math.max(answer, key * val);
							longest = Math.max(longest, val);
							remove.add(key);
						}
						else { // 작은거 이어가기
							map.put(key, val + 1);
						}
					}
					
					for (int key : remove) { // 걸러낸거 삭제
						map.remove(key);
					}
					
					if (!map.containsKey(num))
						map.put(num, longest + 1);
				}
				
				else { // 이전보다 크거나 같으면
					for (int key : map.keySet()) {
						map.put(key, map.get(key) + 1);
					}
					
					if (num > preNum) // 클 경우 새로 추가
						map.put(num, 1);
				}
				
				preNum = num;
			}
			
			for (int key : map.keySet()) {
				int val = map.get(key);
				answer = Math.max(answer, key * val);
			}
			
			System.out.println(answer);
		}
	}
}