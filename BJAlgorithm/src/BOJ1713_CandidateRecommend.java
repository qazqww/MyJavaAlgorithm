// https://www.acmicpc.net/problem/1713

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1713_CandidateRecommend {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int cnt = Integer.parseInt(in.readLine());
		
		Candidate[] arr = new Candidate[N];
		Arrays.fill(arr, new Candidate(0, 0, -1));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int t = 0;
		while (st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				if (arr[i].no == num) {
					arr[i].recommended++;
					break;
				}
				if (i == N - 1) {
					arr[N - 1] = new Candidate(num, 1, t);
				}
			}
			
			Arrays.sort(arr);
			t++;
		}

		Arrays.sort(arr, (a, b) -> a.no - b.no); 
		StringBuilder sb = new StringBuilder();
		for (Candidate c : arr) {
			if (c.no == 0)
				continue;
			sb.append(c.no + " ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static class Candidate implements Comparable<Candidate> {
		int no;
		int recommended;
		int time;
		public Candidate(int no, int recommended, int time) {
			this.no = no;
			this.recommended = recommended;
			this.time = time;
		}
		@Override
		public int compareTo(Candidate o) {
			if (this.recommended == o.recommended)
				return o.time - this.time;
			return o.recommended - this.recommended;
		}
	}
}