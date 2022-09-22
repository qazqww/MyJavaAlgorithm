// https://www.acmicpc.net/problem/1654

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1654_CutLAN {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = in.readLine().split(" ");
		
		int K = Integer.parseInt(temp[0]);
		int N = Integer.parseInt(temp[1]);
		
		PriorityQueue<NumInfo> pq = new PriorityQueue<>((a,b) -> b.num/b.cnt - a.num/a.cnt);
		int answer = -1;
		int n = 0;
		
		for (int i = 0; i < K; i++) {
			pq.offer(new NumInfo(Integer.parseInt(in.readLine()), 1));
		}
		
		while (n < N) {
			NumInfo info = pq.poll();
			answer = info.num / info.cnt;
			n++;
			pq.offer(new NumInfo(info.num, info.cnt + 1));
		}
		
		System.out.println(answer);
	}
	
	static class NumInfo {
		int num;
		int cnt;
		public NumInfo(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
}
