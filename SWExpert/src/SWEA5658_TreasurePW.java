// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA5658_TreasurePW {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String num = in.readLine();
			
			PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
			
			int unit = N/4; // 새로운 수가 생기는 유효한 회전 수
			for (int k = 0; k < unit; k++) {
				for (int i = 0; i < 4; i++) { // 각 변의 수 구하기
					String temp = num.substring(i * unit, (i+1) * unit);
					int dec = hexToDec(temp);
					if (!pq.contains(dec))
						pq.offer(dec);
				}
				
				if (k != unit - 1) // 시계방향으로 돌리기
					num = num.substring(1, num.length()) + num.charAt(0);
			}
			
			for (int i = 0; i < K - 1; i++) // K번째 전까지의 수 모두 빼내기
				pq.poll();
			
			System.out.printf("#%d %d\n", t+1, pq.poll());
		}
		
	}
	
	// 16진수를 받아 10진수로 반환
	static int hexToDec(String str) {
		int result = 0;
		
		for (int i = 0; i < str.length(); i++) {
			int ch = str.charAt(i);
			
			if (ch <= '9') ch -= '0';
			else ch = ch - 'A' + 10;
			
			result += ch * Math.pow(16, str.length() - i - 1);
		}
		
		return result;
	}
}