// https://www.acmicpc.net/problem/10800

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ10800_ColorBall {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine()); // 공의 개수
		PriorityQueue<Ball> pq = new PriorityQueue<>(); // 공의 크기를 기준으로 정렬하는 우선순위 큐
		int[] result = new int[N]; // 잡을 수 있는 공들의 크기(답)
		int[] sumArr = new int[N+1]; // 색깔 별로 크기의 합
		int sum = 0; // 모든 공 크기의 합
		
		boolean isSame = false; // 이전 공과 크기가 같은지
		int preSize = -1; // 이전 공 크기
		int preColor = -1; // 이전 공 색깔
		int[] preStack = new int[N+1]; // 크기가 같았던 공들의 개수를 색깔별로 세둔다
		int preSum = 0; // 크기가 같았던 공들의 개수를 센다
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			pq.offer(new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!pq.isEmpty()) {
			Ball b = pq.poll(); // 공을 우선순위 큐에서 빼낸다 (작은 것부터 나옴)
			
			// 일반적인 경우 : 이전까지의 공 크기 합에서 자신과 색깔이 같은 공들의 크기를 뺀다
			sumArr[b.color] += b.size;
			sum += b.size;
			result[b.no] = sum - sumArr[b.color];
			
			// 크기가 같은 경우 : 추가로 자신과 색깔이 다르면서 크기는 같았던 공들을 뺀다
			if (b.size == preSize) {
				isSame = true;
				preStack[preColor] += 1;
				preSum++;
				result[b.no] -= (preSum - preStack[b.color]) * b.size;
			}
			// 이전까지는 크기가 같았고, 현재부터는 크기가 다른 경우 : 이전 공에 관한 정보를 담았던 것들을 초기화해준다.
			else if (isSame){
				Arrays.fill(preStack, 0);
				preSum = 0;
				isSame = false;
			}
			
			// 크기가 같은 공이 생길 것을 대비하여, 다음으로 넘어가기 전 현재 공의 크기와 색을 담는다.
			preSize = b.size;
			preColor = b.color;
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println(result[i]);
		}
	}
	
	static class Ball implements Comparable<Ball>{
		int no;
		int color;
		int size;
		
		public Ball(int no, int color, int size) {
			this.no = no;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {
			return Integer.compare(size, o.size);
		}
	}
}